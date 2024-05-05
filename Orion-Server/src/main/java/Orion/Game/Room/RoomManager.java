package Orion.Game.Room;

import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.IRoomManager;
import com.google.inject.Singleton;

import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class RoomManager implements IRoomManager {
    private final ConcurrentHashMap<Integer, IRoom> rooms;

    public RoomManager() {
        this.rooms = new ConcurrentHashMap<>();
    }

    @Override
    public void addRoom(IRoom room) {
        this.rooms.putIfAbsent(room.getData().getId(), room);
    }

    @Override
    public IRoom getRoomById(int roomId) {
        return this.rooms.get(roomId);
    }
}
