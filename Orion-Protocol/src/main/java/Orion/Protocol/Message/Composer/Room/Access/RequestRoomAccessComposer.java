package Orion.Protocol.Message.Composer.Room.Access;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RequestRoomAccessComposer extends MessageComposer {
    public RequestRoomAccessComposer(final String username) {
        super(ComposerHeaders.RequestRoomAccessComposer);

        appendString(username);
    }
}
