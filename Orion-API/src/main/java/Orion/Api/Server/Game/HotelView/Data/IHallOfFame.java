package Orion.Api.Server.Game.HotelView.Data;

import java.util.List;

public interface IHallOfFame {
    void setHabboWinners(final List<IHallOfFameWinner> habboWinners);

    List<IHallOfFameWinner> getHabboWinners();

    String getCompetitionName();
}
