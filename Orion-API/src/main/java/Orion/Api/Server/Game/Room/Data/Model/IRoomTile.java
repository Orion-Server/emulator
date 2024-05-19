package Orion.Api.Server.Game.Room.Data.Model;

import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Util.IPositionable;
import Orion.Api.Util.Initializable;

public interface IRoomTile extends IPositionable, Initializable {
    double getStackHeight();

    void onEntityLeave(IRoomEntity entity);

    void onEntityEnter(IRoomEntity entity);

    double getWalkHeight();
}
