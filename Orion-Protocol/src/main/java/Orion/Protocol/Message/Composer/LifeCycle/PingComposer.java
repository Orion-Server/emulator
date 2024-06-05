package Orion.Protocol.Message.Composer.LifeCycle;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class PingComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.PingComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {

    }
}
