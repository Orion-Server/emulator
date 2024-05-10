package Orion.Game.Room.Data.Model;

import Orion.Api.Server.Game.Room.Component.IRoomMappingComponent;
import Orion.Api.Server.Game.Room.Data.Model.IRoomTile;
import Orion.Api.Server.Game.Util.Position;

public class RoomTile implements IRoomTile {
    private final IRoomMappingComponent mappingComponent;

    private final Position position;

    public RoomTile(
            IRoomMappingComponent mappingComponent,
            final Position position
    ) {
        this.mappingComponent = mappingComponent;
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {

    }

    @Override
    public double getStackHeight() {
        // TODO: Implement
        return 0d;
    }
}
