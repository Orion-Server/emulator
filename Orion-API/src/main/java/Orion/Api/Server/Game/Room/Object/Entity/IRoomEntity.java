package Orion.Api.Server.Game.Room.Object.Entity;

import Orion.Api.Server.Game.Room.Object.IRoomObject;
import Orion.Api.Util.IWriteable;

public interface IRoomEntity extends IRoomObject, IWriteable {
    int getHeadRotation();

    int getBodyRotation();

    void setHeadRotation(int headRotation);

    void setBodyRotation(int bodyRotation);
}
