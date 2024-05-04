package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class UserRightsComposer extends MessageComposer {
    public UserRightsComposer() {
        super(ComposerHeaders.UserRightsComposer);

        appendInt(2);
        appendInt(1);
        appendBoolean(false);
    }
}
