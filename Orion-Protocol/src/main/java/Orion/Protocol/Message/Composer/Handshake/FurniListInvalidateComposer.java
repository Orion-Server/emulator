package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class FurniListInvalidateComposer extends MessageComposer {
    public FurniListInvalidateComposer() {
        super(ComposerHeaders.FurniListInvalidateComposer);
    }
}
