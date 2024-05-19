package Orion.Game.Room.Component;

import Orion.Api.Server.Game.Room.Component.IRoomMappingComponent;
import Orion.Api.Server.Game.Room.Data.Model.Enum.RoomTileState;
import Orion.Api.Server.Game.Room.Data.Model.IRoomTile;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.IRoomObject;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Api.Server.Game.Room.Object.Pathfinder.RoomEntityMovementNode;
import Orion.Api.Server.Game.Util.Position;
import Orion.Game.Room.Data.Model.RoomTile;

import java.util.List;

public class RoomMappingComponent implements IRoomMappingComponent {
    private final IRoom room;

    private IRoomTile[][] tiles;

    public RoomMappingComponent(final IRoom room) {
        this.room = room;
    }

    @Override
    public void beforeInitialize() {
        if(this.room.getModel() == null) return;

        final int sizeX = this.room.getModel().getMapSizeX();
        final int sizeY = this.room.getModel().getMapSizeY();

        this.tiles = new IRoomTile[sizeX][sizeY];

        for(int x = 0; x < sizeX; x++) {
            final IRoomTile[] xRow = new RoomTile[sizeY];

            for(int y = 0; y < sizeY; y++) {
                final IRoomTile tile = new RoomTile(this.room, new Position(x, y, this.room.getModel().getSquareHeight()[x][y]));

                xRow[y] = tile;
            }

            this.tiles[x] = xRow;
        }
    }

    @Override
    public void initialize() {
        for (IRoomTile[] iRoomTiles : this.tiles) {
            for (final IRoomTile tile : iRoomTiles) {
                tile.initialize();
            }
        }
    }

    @Override
    public IRoomTile getTile(final Position position) {
        return this.tiles[position.getX()][position.getY()];
    }

    @Override
    public IRoomTile getTile(final int x, final int y) {
        return this.tiles[x][y];
    }

    @Override
    public IRoomTile getDoorTile() {
        return this.getTile(this.room.getModel().getData().getDoorX(), this.room.getModel().getData().getDoorY());
    }

    @Override
    public boolean isValidPosition(
            final IRoomObject object,
            final Position from,
            final Position to,
            final boolean isLastStep,
            final boolean isRetry,
            final boolean firstGeneration
    ) {
        if(from.equals(to)) return false;

        if(!this.isValidPosition(to) || this.room.getModel().getSquareState()[to.getX()][to.getY()] == RoomTileState.INVALID) return false;

        if(this.getDoorTile().getPosition().equals(to) && !isLastStep) return false;

        final IRoomTile tile = this.getTile(to);

        if(tile == null) return false;

        if(tile.getMovementNode() == RoomEntityMovementNode.CLOSED || (tile.getMovementNode() == RoomEntityMovementNode.END_OF_ROUTE && !isLastStep)) return false;

        return true;
    }

    @Override
    public boolean isValidPosition(Position position) {
        return position.getX() >= 0 && position.getY() >= 0 && position.getX() < this.room.getModel().getMapSizeX() && position.getY() < this.room.getModel().getMapSizeY();
    }

    @Override
    public void dispose() {
        this.tiles = null;
    }
}
