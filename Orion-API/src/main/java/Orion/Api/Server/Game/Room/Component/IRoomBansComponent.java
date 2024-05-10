package Orion.Api.Server.Game.Room.Component;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.Data.Ban.IRoomBan;

import java.util.List;

public interface IRoomBansComponent {
    void setBans(List<IRoomBan> bans);

    boolean habboIsBanned(IHabbo habbo);
}
