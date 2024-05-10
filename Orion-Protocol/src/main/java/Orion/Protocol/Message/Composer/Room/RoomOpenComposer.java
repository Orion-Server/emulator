package Orion.Protocol.Message.Composer.Room;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomOpenComposer extends MessageComposer {
    public RoomOpenComposer() {
        super(ComposerHeaders.RoomOpenComposer);
    }
}
