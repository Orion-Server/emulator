package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomPromotionComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.RoomPromotionComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(-1);
        msg.appendInt(-1);
        msg.appendString("");
        msg.appendInt(0);
        msg.appendInt(0);
        msg.appendString("");
        msg.appendString("");
        msg.appendInt(0);
        msg.appendInt(0);
        msg.appendInt(0);
    }
}
