package Orion.Game.Room.Data.Model;

import Orion.Api.Server.Game.Room.Data.Model.Enum.RoomTileState;
import Orion.Api.Server.Game.Room.Data.Model.IRoomTile;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Api.Server.Game.Room.Object.Item.IRoomItem;
import Orion.Api.Server.Game.Room.Object.Pathfinder.RoomEntityMovementNode;
import Orion.Api.Server.Game.Room.Object.Pathfinder.RoomTileStatusType;
import Orion.Api.Server.Game.Util.Position;

import java.util.ArrayList;
import java.util.List;

public class RoomTile implements IRoomTile {
    private final IRoom room;

    private final Position position;

    private RoomEntityMovementNode movementNode;

    private RoomTileStatusType statusType;

    private RoomTileState state;

    private IRoomItem topItem;

    private final List<IRoomFloorItem> floorItems;

    private boolean canPlaceItems;

    private boolean canStack;

    private Position redirectTo;

    private double stackHeight;

    private double originalHeight;

    public RoomTile(final IRoom room, final Position position) {
        this.room = room;
        this.position = position;
        this.floorItems = new ArrayList<>();
    }

    @Override
    public void initialize() {
        final RoomTileState modelState = this.room.getModel().getSquareState()[this.getPosition().getX()][this.getPosition().getY()];

        this.state = modelState == null ? RoomTileState.INVALID : modelState;
        this.canPlaceItems = modelState == null || modelState.equals(RoomTileState.VALID);
        this.originalHeight = this.room.getModel().getSquareHeight()[this.getPosition().getX()][this.getPosition().getY()];

        double highestHeight = 0d;

        for(final IRoomFloorItem item : this.floorItems) {
            if(item.getDefinition() == null) continue;

            // TODO: Check gate interaction

            double itemHeight = item.getData().getPosition().getZ() + item.getDefinition().getStackHeight(); // TODO: Check override height

            if(itemHeight > highestHeight) {
                highestHeight = itemHeight;
                this.topItem = item;
            }

            if(!item.getDefinition().isAllowWalk() && this.topItem.getData().getId() == item.getData().getId()) { // TODO: Check gate
                movementNode = RoomEntityMovementNode.CLOSED;
            }

            switch (item.getDefinition().getInteractionType()) {
                case "bed":
                    statusType = RoomTileStatusType.LAY;
                    movementNode = RoomEntityMovementNode.END_OF_ROUTE;

                    if(item.getRotation() == 2 || item.getRotation() == 6) {
                        this.redirectTo = item.getPosition().copy();
                        break;
                    }

                    if(item.getRotation() == 0 || item.getRotation() == 4) {
                        this.redirectTo = this.position.copy();
                    }

                    break;

                case "gate":
                    // TODO: Check others interactions
                    break;
            }

            // TODO: BreedingBox
            // TODO: SnowBoard

            if(item.getDefinition().isAllowSit()) {
                statusType = RoomTileStatusType.SIT;
                movementNode = RoomEntityMovementNode.END_OF_ROUTE;
            }

            if (item.getDefinition().getInteractionType().equals("bed")) {
                statusType = RoomTileStatusType.LAY;
                movementNode = RoomEntityMovementNode.END_OF_ROUTE;
            }

            if(!item.getDefinition().isAllowStack()) {
                this.canStack = false;
            }

            // TODO: Check item override height
        }

        this.stackHeight = highestHeight;

        if(this.stackHeight == 0d) {
            this.stackHeight = this.originalHeight;
        }
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {

    }

    @Override
    public void addItem(IRoomFloorItem item) {
        this.floorItems.add(item);
    }

    @Override
    public void onEntityLeave(IRoomEntity entity) {

    }

    @Override
    public void onEntityEnter(IRoomEntity entity) {
        if(this.topItem == null) return;

        this.topItem.getInteraction().onEntityEnter(entity);
    }

    @Override
    public double getStackHeight() {
        return this.stackHeight;
    }

    @Override
    public double getWalkHeight() {
        if(this.topItem == null) {
            return this.stackHeight;
        }

        if(this.topItem.getDefinition().isAllowSit() || this.topItem.getDefinition().isAllowLay()) { // TODO: Check snowboard and roller
            return this.stackHeight - this.topItem.getDefinition().getStackHeight();
        }

        return this.stackHeight;
    }

    @Override
    public RoomEntityMovementNode getMovementNode() {
        return this.movementNode;
    }
}
