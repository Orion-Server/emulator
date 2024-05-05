package Orion.Api.Server.Game.Room;

public interface IRoomManager {
    IRoom getRoomById(int roomId);

    void addRoom(IRoom room);
}
