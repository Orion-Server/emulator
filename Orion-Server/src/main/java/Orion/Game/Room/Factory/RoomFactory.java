package Orion.Game.Room.Factory;

import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.IRoomManager;
import Orion.Api.Storage.Result.IConnectionResult;
import Orion.Game.Room.Room;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class RoomFactory {
    @Inject
    private Injector injector;

    @Inject
    private IRoomManager roomManager;

    public IRoom create(IConnectionResult data) {
        final IRoom room = new Room(data);

        this.injector.injectMembers(room);

        this.roomManager.addRoom(room);

        return room;
    }
}
