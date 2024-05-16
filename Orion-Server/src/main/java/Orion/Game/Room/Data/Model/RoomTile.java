package Orion.Game.Room.Data.Model;

import Orion.Api.Server.Game.Room.Component.IRoomMappingComponent;
import Orion.Api.Server.Game.Room.Data.Model.IRoomTile;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
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
    public void onEntityLeave(IRoomEntity entity) {
        System.out.println(STR."Leaving on roomTile in the position \{this.position.getX()},\{this.position.getY()}");
    }

    @Override
    public void onEntityEnter(IRoomEntity entity) {
        System.out.println(STR."Entering on roomTile in the position \{this.position.getX()},\{this.position.getY()}");
    }

    @Override
    public double getStackHeight() {
        // TODO: Implement
        return 0d;
    }
}
