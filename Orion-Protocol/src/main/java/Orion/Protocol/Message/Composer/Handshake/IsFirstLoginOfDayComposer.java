package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class IsFirstLoginOfDayComposer extends MessageComposer {
    public IsFirstLoginOfDayComposer() {
        super(ComposerHeaders.IsFirstLoginOfDayComposer);

        appendBoolean(true);
    }
}
