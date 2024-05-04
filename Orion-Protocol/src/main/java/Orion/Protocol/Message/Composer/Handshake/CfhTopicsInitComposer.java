package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class CfhTopicsInitComposer extends MessageComposer {
    public CfhTopicsInitComposer() {
        super(ComposerHeaders.CfhTopicsInitComposer);

        appendInt(0);
    }
}
