package Orion.Protocol.Message.Composer.MysteryBox;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class MysteryBoxKeysComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.MysteryBoxKeysComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendString("");
        msg.appendString("");
    }
}
