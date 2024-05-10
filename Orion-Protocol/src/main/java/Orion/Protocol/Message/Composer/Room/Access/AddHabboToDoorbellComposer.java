package Orion.Protocol.Message.Composer.Room.Access;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class AddHabboToDoorbellComposer extends MessageComposer {
    public AddHabboToDoorbellComposer(final String username) {
        super(ComposerHeaders.AddHabboToDoorbellComposer);

        appendString(username);
    }
}
