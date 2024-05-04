package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class FigureSetIdsComposer extends MessageComposer {
    public FigureSetIdsComposer() {
        super(ComposerHeaders.FigureSetIdsComposer);

        appendInt(0);
        appendInt(0);
    }
}
