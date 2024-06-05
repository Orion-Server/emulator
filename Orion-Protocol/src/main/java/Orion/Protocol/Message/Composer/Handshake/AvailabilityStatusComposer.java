package Orion.Protocol.Message.Composer.Handshake;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class AvailabilityStatusComposer extends Composer {
    private final boolean isOpen;
    private final boolean isShuttingDown;
    private final boolean isAuthenticationOk;

    public AvailabilityStatusComposer(
            final boolean isOpen,
            final boolean isShuttingDown,
            final boolean isAuthenticationOk
    ) {
        this.isOpen = isOpen;
        this.isShuttingDown = isShuttingDown;
        this.isAuthenticationOk = isAuthenticationOk;
    }

    @Override
    public short getId() {
        return ComposerHeaders.AvailabilityStatusComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendBoolean(this.isOpen);
        msg.appendBoolean(this.isShuttingDown);
        msg.appendBoolean(this.isAuthenticationOk);
    }
}
