package Orion.Api.Server.Game.Room.Data.Ban;

import Orion.Api.Util.IFillable;

public interface IRoomBan extends IFillable {
    int getRoomId();

    int getUserId();

    String getUsername();

    long getEndTimestamp();
}
