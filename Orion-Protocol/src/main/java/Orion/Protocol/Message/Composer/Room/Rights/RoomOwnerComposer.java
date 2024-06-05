package Orion.Protocol.Message.Composer.Room.Rights;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomOwnerComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.RoomOwnerComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {

    }
}
