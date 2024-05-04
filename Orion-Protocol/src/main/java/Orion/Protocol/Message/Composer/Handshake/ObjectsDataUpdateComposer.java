package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class ObjectsDataUpdateComposer extends MessageComposer {
    public ObjectsDataUpdateComposer() {
        super(ComposerHeaders.ObjectsDataUpdateComposer);

        appendInt(Integer.MAX_VALUE);
        appendInt(0);
        appendInt(100);
        appendInt(Integer.MAX_VALUE);
        appendInt(0);
    }
}
