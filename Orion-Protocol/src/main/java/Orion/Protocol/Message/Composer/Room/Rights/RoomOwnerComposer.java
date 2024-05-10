package Orion.Protocol.Message.Composer.Room.Rights;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomOwnerComposer extends MessageComposer {
    public RoomOwnerComposer() {
        super(ComposerHeaders.RoomOwnerComposer);
    }
}
