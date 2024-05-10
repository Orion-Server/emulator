package Orion.Protocol.Message.Composer.Room.Object;

import Orion.Api.Server.Game.Room.IRoom;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomFloorObjectsComposer extends MessageComposer {
    public RoomFloorObjectsComposer(final IRoom room) {
        super(ComposerHeaders.RoomFloorObjectsComposer);

        appendInt(0);
        appendInt(0);
    }
}
