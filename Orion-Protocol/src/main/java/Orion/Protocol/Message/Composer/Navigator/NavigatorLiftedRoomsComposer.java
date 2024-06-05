package Orion.Protocol.Message.Composer.Navigator;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class NavigatorLiftedRoomsComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.NavigatorLiftedRoomsComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(0);
    }
}