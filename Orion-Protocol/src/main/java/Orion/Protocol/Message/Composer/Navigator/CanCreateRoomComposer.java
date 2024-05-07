package Orion.Protocol.Message.Composer.Navigator;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class CanCreateRoomComposer extends MessageComposer {
    public CanCreateRoomComposer(int canCreateRoom, int roomLimit) {
        super(ComposerHeaders.CanCreateRoomComposer);

        appendInt(canCreateRoom);
        appendInt(roomLimit);
    }
}
