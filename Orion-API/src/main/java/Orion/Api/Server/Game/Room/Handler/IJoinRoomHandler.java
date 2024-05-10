package Orion.Api.Server.Game.Room.Handler;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.IRoom;

public interface IJoinRoomHandler {
    void prepareRoom(IRoom room, IHabbo habbo, String password);

    void finalizeRoomEnter(IRoom room, IHabbo habbo);

    void finalizeEntryPackets(IRoom room, IHabbo habbo);
}
