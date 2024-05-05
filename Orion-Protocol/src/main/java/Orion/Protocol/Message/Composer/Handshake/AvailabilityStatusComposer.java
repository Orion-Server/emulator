package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class AvailabilityStatusComposer extends MessageComposer {
    public AvailabilityStatusComposer(
            final boolean isOpen,
            final boolean isShuttingDown,
            final boolean isAuthenticationOk
    ) {
        super(ComposerHeaders.AvailabilityStatusComposer);

        appendBoolean(isOpen);
        appendBoolean(isShuttingDown);
        appendBoolean(isAuthenticationOk);
    }
}
