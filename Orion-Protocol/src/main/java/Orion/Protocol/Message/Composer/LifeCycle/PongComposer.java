package Orion.Protocol.Message.Composer.LifeCycle;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class PongComposer extends Composer {
    private final int ping;

    public PongComposer(final int ping) {
        this.ping = ping;
    }

    @Override
    public short getId() {
        return ComposerHeaders.PongComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.ping);
    }
}
