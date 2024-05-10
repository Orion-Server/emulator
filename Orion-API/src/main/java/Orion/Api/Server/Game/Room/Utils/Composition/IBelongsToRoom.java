package Orion.Api.Server.Game.Room.Utils.Composition;

import Orion.Api.Server.Game.Room.IRoom;

public interface IBelongsToRoom {
    IRoom getRoom();

    int getVirtualId();
}
