package Orion.Protocol.Message.Composer.Room;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RemoveHabboEntityComposer extends MessageComposer {
    public RemoveHabboEntityComposer(final int virtualId) {
        super(ComposerHeaders.RemoveHabboEntityComposer);

        appendString(String.valueOf(virtualId));
    }
}
