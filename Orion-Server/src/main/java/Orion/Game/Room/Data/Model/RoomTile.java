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
import io.netty.util.internal.ConcurrentSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

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

    private final Set<IRoomEntity> entities;

    private Map<Integer, Consumer<IRoomEntity>> pendingEvents;

    public RoomTile(final IRoom room, final Position position) {
        this.room = room;
        this.position = position;
        this.floorItems = new ArrayList<>();
        this.entities = ConcurrentHashMap.newKeySet();

        this.pendingEvents = new ConcurrentHashMap<>();
    }

    @Override
    public void initialize() {
        final RoomTileState modelState = this.room.getModel().getSquareState()[this.getPosition().getX()][this.getPosition().getY()];

        this.canStack = true;
        this.stackHeight = 0d;
        this.statusType = RoomTileStatusType.NONE;
        this.movementNode = RoomEntityMovementNode.OPEN;
        this.state = modelState == null ? RoomTileState.INVALID : modelState;
        this.canPlaceItems = modelState != null && modelState.equals(RoomTileState.VALID);
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
                this.movementNode = RoomEntityMovementNode.CLOSED;
            }

            switch (item.getDefinition().getInteractionType()) {
                case "bed":
                    this.statusType = RoomTileStatusType.LAY;
                    this.movementNode = RoomEntityMovementNode.END_OF_ROUTE;

                    if(item.getData().getRotation() == 2 || item.getData().getRotation() == 6) {
                        this.redirectTo = item.getPosition().copy();
                        break;
                    }

                    if(item.getData().getRotation() == 0 || item.getData().getRotation() == 4) {
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
                this.statusType = RoomTileStatusType.SIT;
                this.movementNode = RoomEntityMovementNode.END_OF_ROUTE;
            }

            if (item.getDefinition().getInteractionType().equals("bed")) {
                this.statusType = RoomTileStatusType.LAY;
                this.movementNode = RoomEntityMovementNode.END_OF_ROUTE;
            }

            if(!item.getDefinition().isAllowStack()) {
                this.canStack = false;
            }

            // TODO: Check item override height
        }

        this.stackHeight = highestHeight;

        if(this.originalHeight == 0d) {
            this.originalHeight = this.stackHeight;
        }
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {}

    @Override
    public void addItem(IRoomFloorItem item) {
        final int lastTopItemId = this.topItem == null ? 0 : this.topItem.getData().getId();

        this.floorItems.add(item);

        this.initialize();

        if(this.topItem != null && this.topItem.getData().getId() != lastTopItemId) {
            for (final IRoomEntity entity : this.entities) {
                this.topItem.getInteraction().onEntityEnter(entity);
            }
        }
    }

    @Override
    public void removeItem(IRoomFloorItem item) {
        if(this.topItem != null && this.topItem.getData().getId() == item.getData().getId()) {
            for (final IRoomEntity entity : this.entities) {
                this.topItem.getInteraction().onEntityLeave(entity);
            }

            this.topItem = null;
        }

        this.floorItems.remove(item);
        this.initialize();
    }

    @Override
    public void onEntityLeave(IRoomEntity entity) {
        this.entities.remove(entity);

        if(this.topItem == null) return;

        this.topItem.getInteraction().onEntityLeave(entity);
    }

    @Override
    public void onEntityEnter(IRoomEntity entity) {
        this.entities.add(entity);
        this.executePendingEvents(entity);

        if(this.topItem == null) return;

        this.topItem.getInteraction().onEntityEnter(entity);
    }

    @Override
    public double getStackHeight() {
        return this.stackHeight;
    }

    @Override
    public void scheduleEvent(int entityId, Consumer<IRoomEntity> event) {
        this.pendingEvents.put(entityId, event);
    }

    @Override
    public void clearScheduledEvent(int entityId) {
        this.pendingEvents.remove(entityId);
    }

    private void executePendingEvents(IRoomEntity entity) {
        final Consumer<IRoomEntity> event = this.pendingEvents.remove(entity.getVirtualId());

        if(event == null) return;

        event.accept(entity);
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

    @Override
    public boolean canPlaceItems() {
        return this.canPlaceItems;
    }

    @Override
    public boolean canStack() {
        return this.canStack;
    }

    @Override
    public RoomTileStatusType getStatusType() {
        return this.statusType;
    }

    @Override
    public RoomTileState getState() {
        return this.state;
    }

    @Override
    public IRoomItem getTopItem() {
        return this.topItem;
    }

    @Override
    public Position getRedirectTo() {
        return this.redirectTo;
    }

    @Override
    public Set<IRoomEntity> getEntities() {
        return this.entities;
    }

    @Override
    public void dispose() {
        this.floorItems.clear();
        this.entities.clear();
        this.pendingEvents.clear();
    }
}
