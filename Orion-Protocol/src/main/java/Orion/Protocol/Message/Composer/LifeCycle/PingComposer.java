package Orion.Protocol.Message.Composer.LifeCycle;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class PingComposer extends MessageComposer {
    public PingComposer() {
        super(ComposerHeaders.PingComposer);
    }
}
