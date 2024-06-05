package Orion.Protocol.Message.Composer.HotelView;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.HotelView.Data.IHallOfFame;
import Orion.Api.Server.Game.HotelView.Data.IHallOfFameWinner;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HallOfFameComposer extends Composer {
    private final IHallOfFame hallOfFame;

    public HallOfFameComposer(final IHallOfFame hallOfFame) {
        this.hallOfFame = hallOfFame;
    }

    @Override
    public short getId() {
        return ComposerHeaders.HallOfFameComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendString(this.hallOfFame.getCompetitionName());
        msg.appendString(this.hallOfFame.getHabboWinners().size());

        int number = 0;

        for (final IHallOfFameWinner winner : this.hallOfFame.getHabboWinners()) {
            msg.appendInt(winner.getId());
            msg.appendString(winner.getUsername());
            msg.appendString(winner.getLook());
            msg.appendInt(number);
            msg.appendInt(winner.getPoints());

            number++;
        }
    }
}
