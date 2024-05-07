package Orion.Game.Room;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Data.IRoomData;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Storage.Result.IConnectionResult;
import Orion.Game.Room.Data.RoomData;
import Orion.Writer.Room.RoomWriter;

public class Room implements IRoom {
    private final IRoomData data;

    private boolean isFullyLoaded = false;

    public Room(final IConnectionResult data) {
        this.data = new RoomData(data);
    }

    @Override
    public IRoomData getData() {
        return this.data;
    }

    @Override
    public boolean isFullyLoaded() {
        return this.isFullyLoaded;
    }

    @Override
    public void setFullyLoaded(boolean isFullyLoaded) {
        this.isFullyLoaded = isFullyLoaded;
    }

    @Override
    public void write(final IMessageComposer messageComposer) {
        RoomWriter.write(this, messageComposer);
    }

    @Override
    public int compareTo(IRoom o) {
        return 0;
    }
}
