package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class AvailabilityStatusComposer extends MessageComposer {
    public AvailabilityStatusComposer() {
        super(ComposerHeaders.AvailabilityStatusComposer);

        appendBoolean(true);
        appendBoolean(false);
        appendBoolean(true);
    }
}
