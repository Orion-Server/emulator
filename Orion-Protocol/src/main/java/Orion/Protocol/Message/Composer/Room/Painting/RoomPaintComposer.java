package Orion.Protocol.Message.Composer.Room.Painting;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomPaintComposer extends MessageComposer {
    public RoomPaintComposer(
            final String type,
            final String value
    ) {
        super(ComposerHeaders.RoomPaintComposer);

        appendString(type);
        appendString(value);
    }
}
