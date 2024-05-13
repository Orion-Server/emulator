package Orion.Api.Server.Game.Room.Object.Entity;

import Orion.Api.Server.Game.Room.Object.IRoomObject;
import Orion.Api.Server.Game.Util.Position;
import Orion.Api.Util.IStatusable;
import Orion.Api.Util.IWriteable;

public interface IRoomEntity extends IRoomObject, IWriteable, IStatusable {
    int getHeadRotation();

    int getBodyRotation();

    void setHeadRotation(int headRotation);

    void setBodyRotation(int bodyRotation);

    Position getNextPosition();

    void setNextPosition(Position position);
}
