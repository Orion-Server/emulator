package Orion.Api.Server.Game.Room.Process;

import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Util.IDisposable;
import Orion.Api.Util.Initializable;

public interface IRoomProcess extends IDisposable, Initializable, Runnable {
    void onEntityRemoved(IRoomEntity entity);
}
