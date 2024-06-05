package Orion.Protocol.Message.Composer.Habbo.Inventory;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class FigureSetIdsComposer extends Composer {
    private final IHabbo habbo;

    public FigureSetIdsComposer(final IHabbo habbo) {
        this.habbo = habbo;
    }

    @Override
    public short getId() {
        return ComposerHeaders.FigureSetIdsComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(0); // TODO: Implement
        msg.appendInt(0);
    }
}
