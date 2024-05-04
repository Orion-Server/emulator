package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class NoobnessLevelComposer extends MessageComposer {
    public NoobnessLevelComposer() {
        super(ComposerHeaders.NooobnessLevelComposer);

        appendInt(1);
    }
}
