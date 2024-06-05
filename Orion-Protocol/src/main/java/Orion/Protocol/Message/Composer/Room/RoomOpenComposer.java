package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomOpenComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.RoomOpenComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {

    }
}
