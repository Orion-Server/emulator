package Orion.Protocol.Message.Composer.Habbo;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class IsFirstLoginOfDayComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.IsFirstLoginOfDayComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendBoolean(true);
    }
}
