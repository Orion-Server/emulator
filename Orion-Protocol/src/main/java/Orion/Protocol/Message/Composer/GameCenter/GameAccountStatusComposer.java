package Orion.Protocol.Message.Composer.GameCenter;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class GameAccountStatusComposer extends Composer {
    private final int gameId;
    private final int tickets;

    public GameAccountStatusComposer(int gameId, int tickets) {
        this.gameId = gameId;
        this.tickets = tickets;
    }

    @Override
    public short getId() {
        return ComposerHeaders.GameAccountStatusComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(gameId);
        msg.appendInt(tickets);
        msg.appendInt(1);
    }
}
