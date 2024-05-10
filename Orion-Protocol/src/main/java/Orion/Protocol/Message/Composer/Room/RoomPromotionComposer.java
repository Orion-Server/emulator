package Orion.Protocol.Message.Composer.Room;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomPromotionComposer extends MessageComposer {
    public RoomPromotionComposer() {
        super(ComposerHeaders.RoomPromotionComposer);

        appendInt(-1);
        appendInt(-1);
        appendString("");
        appendInt(0);
        appendInt(0);
        appendString("");
        appendString("");
        appendInt(0);
        appendInt(0);
        appendInt(0);
    }
}
