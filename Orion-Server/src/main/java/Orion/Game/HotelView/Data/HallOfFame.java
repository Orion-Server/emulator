package Orion.Game.HotelView.Data;

import Orion.Api.Server.Game.HotelView.Data.IHallOfFame;
import Orion.Api.Server.Game.HotelView.Data.IHallOfFameWinner;

import java.util.ArrayList;
import java.util.List;

public class HallOfFame implements IHallOfFame {
    private final String competitionName = "xmasRoomComp"; // TODO: Fetch from database

    private final List<IHallOfFameWinner> habboWinners;

    public HallOfFame() {
        this.habboWinners = new ArrayList<>();
    }

    @Override
    public void setHabboWinners(final List<IHallOfFameWinner> habboWinners) {
        this.habboWinners.clear();
        this.habboWinners.addAll(habboWinners);
    }

    @Override
    public List<IHallOfFameWinner> getHabboWinners() {
        return this.habboWinners;
    }

    @Override
    public String getCompetitionName() {
        return this.competitionName;
    }
}
