package Orion.Protocol.Message.Composer.HotelView;

import Orion.Api.Server.Game.HotelView.Data.IHallOfFame;
import Orion.Api.Server.Game.HotelView.Data.IHallOfFameWinner;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HallOfFameComposer extends MessageComposer {
    public HallOfFameComposer(
            final IHallOfFame hallOfFame
    ) {
        super(ComposerHeaders.HallOfFameComposer);

        appendString(hallOfFame.getCompetitionName());
        appendString(hallOfFame.getHabboWinners().size());

        int number = 0;

        for (final IHallOfFameWinner winner : hallOfFame.getHabboWinners()) {
            appendInt(winner.getId());
            appendString(winner.getUsername());
            appendString(winner.getLook());
            appendInt(number);
            appendInt(winner.getPoints());

            number++;
        }
    }
}
