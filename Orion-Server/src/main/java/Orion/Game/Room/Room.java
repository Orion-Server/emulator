package Orion.Game.Room;

import Orion.Api.Server.Game.Room.Data.IRoomData;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Storage.Result.IConnectionResult;
import Orion.Game.Room.Data.RoomData;

public class Room implements IRoom {
    private final IRoomData data;

    public Room(final IConnectionResult data) {
        this.data = new RoomData(data);
    }

    @Override
    public IRoomData getData() {
        return this.data;
    }
}
