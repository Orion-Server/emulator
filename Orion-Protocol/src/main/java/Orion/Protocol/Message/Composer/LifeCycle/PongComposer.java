package Orion.Protocol.Message.Composer.LifeCycle;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class PongComposer extends MessageComposer {
    public PongComposer() {
        super(ComposerHeaders.PongComposer);
    }
}
