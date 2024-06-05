package Orion.Protocol.Message.Composer.Habbo;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboNoobnessLevelComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.HabboNoobnessLevelComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(1);
    }
}
