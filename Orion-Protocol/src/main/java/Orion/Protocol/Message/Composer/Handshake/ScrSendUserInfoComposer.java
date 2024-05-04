package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class ScrSendUserInfoComposer extends MessageComposer {
    public ScrSendUserInfoComposer() {
        super(ComposerHeaders.ScrSendUserInfoComposer);

        appendString("HABBO_CLUB".toLowerCase());
        appendInt(0);
        appendInt(0);
        appendInt(0);
        appendInt(0);
        appendBoolean(false);
        appendBoolean(false);
        appendInt(0);
        appendInt(0);
        appendInt(0);
        appendInt(0);
    }
}
