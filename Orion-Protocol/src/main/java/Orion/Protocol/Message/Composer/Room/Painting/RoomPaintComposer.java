package Orion.Protocol.Message.Composer.Room.Painting;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomPaintComposer extends Composer {
    private final String type;
    private final String value;

    public RoomPaintComposer(final String type, final String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public short getId() {
        return ComposerHeaders.RoomPaintComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendString(this.type);
        msg.appendString(this.value);
    }
}
