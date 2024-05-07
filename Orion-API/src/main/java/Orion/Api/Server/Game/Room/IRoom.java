package Orion.Api.Server.Game.Room;

import Orion.Api.Server.Game.Room.Data.IRoomData;
import Orion.Api.Util.IWriteable;

public interface IRoom extends Comparable<IRoom>, IWriteable {
    IRoomData getData();

    boolean isFullyLoaded();

    void setFullyLoaded(boolean isFullyLoaded);
}
