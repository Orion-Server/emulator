package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class AuthenticationOkComposer extends MessageComposer {
    public AuthenticationOkComposer() {
        super(ComposerHeaders.AuthenticationOkComposer);
    }
}
