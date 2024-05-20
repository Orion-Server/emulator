package Orion.Protocol.Message.Composer.Room;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomCreatedComposer extends MessageComposer {
    public RoomCreatedComposer(final int roomId, final String roomName) {
        super(ComposerHeaders.RoomCreatedComposer);

        appendInt(roomId);
        appendString(roomName);
    }
}
