package Orion.Protocol.Message.Composer.GameCenter;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class GameAccountStatusComposer extends MessageComposer {
    public GameAccountStatusComposer(
            int gameId,
            int tickets
    ) {
        super(ComposerHeaders.GameAccountStatusComposer);

        appendInt(gameId);
        appendInt(tickets);
        appendInt(1);
    }
}
