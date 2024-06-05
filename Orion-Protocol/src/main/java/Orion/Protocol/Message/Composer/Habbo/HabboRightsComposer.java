package Orion.Protocol.Message.Composer.Habbo;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboRightsComposer extends Composer {
    private final IHabbo habbo;

    public HabboRightsComposer(final IHabbo habbo) {
        this.habbo = habbo;
    }

    @Override
    public short getId() {
        return ComposerHeaders.HabboRightsComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        // TODO: Implement
        msg.appendInt(2);
        msg.appendInt(1);
        msg.appendBoolean(false);
    }
}
