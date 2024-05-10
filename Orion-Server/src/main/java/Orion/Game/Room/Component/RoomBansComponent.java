package Orion.Game.Room.Component;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.Component.IRoomBansComponent;
import Orion.Api.Server.Game.Room.Data.Ban.IRoomBan;
import Orion.Api.Server.Game.Util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class RoomBansComponent implements IRoomBansComponent {
    private final List<IRoomBan> bans;

    public RoomBansComponent() {
        this.bans = new ArrayList<>();
    }

    @Override
    public void setBans(List<IRoomBan> bans) {
        this.bans.clear();
        this.bans.addAll(bans);
    }

    @Override
    public boolean habboIsBanned(IHabbo habbo) {
        return this.bans.stream().anyMatch(ban -> ban.getUserId() == habbo.getData().getId() && ban.getEndTimestamp() > TimeUtil.now());
    }
}
