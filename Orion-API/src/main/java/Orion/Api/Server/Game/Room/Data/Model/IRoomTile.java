package Orion.Api.Server.Game.Room.Data.Model;

import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Util.IPositionable;

public interface IRoomTile extends IPositionable {
    double getStackHeight();

    void onEntityLeave(IRoomEntity entity);

    void onEntityEnter(IRoomEntity entity);

    double getWalkHeight();
}
