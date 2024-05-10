package Orion.Protocol.Message.Composer.Room.Access;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomAccessDeniedComposer extends MessageComposer {
    public RoomAccessDeniedComposer(final String username) {
        super(ComposerHeaders.RoomAccessDeniedComposer);

        appendString(username);
    }
}
