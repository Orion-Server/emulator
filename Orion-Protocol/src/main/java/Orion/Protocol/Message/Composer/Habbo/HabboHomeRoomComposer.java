package Orion.Protocol.Message.Composer.Habbo;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboHomeRoomComposer extends Composer {
    private final IHabbo habbo;

    public HabboHomeRoomComposer(final IHabbo habbo) {
        this.habbo = habbo;
    }

    @Override
    public short getId() {
        return ComposerHeaders.HabboHomeRoomComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(0);
        msg.appendInt(0);
    }
}
