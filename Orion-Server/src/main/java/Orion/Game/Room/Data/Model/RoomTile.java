package Orion.Game.Room.Data.Model;

import Orion.Api.Server.Game.Room.Data.Model.IRoomTile;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Server.Game.Util.Position;

public class RoomTile implements IRoomTile {
    private final IRoom room;

    private final Position position;

    public RoomTile(final IRoom room, final Position position) {
        this.room = room;
        this.position = position;
    }

    @Override
    public void initialize() {

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

    @Override
    public double getWalkHeight() {
        // TODO: Implement
        return 0d;
    }
}
