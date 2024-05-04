package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class UserHomeRoomComposer extends MessageComposer {
    public UserHomeRoomComposer() {
        super(ComposerHeaders.UserHomeRoomComposer);

        appendInt(0);
        appendInt(0);
    }
}
