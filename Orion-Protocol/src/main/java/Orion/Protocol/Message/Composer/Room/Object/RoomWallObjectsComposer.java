package Orion.Protocol.Message.Composer.Room.Object;

import Orion.Api.Server.Game.Room.IRoom;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomWallObjectsComposer extends MessageComposer {
    public RoomWallObjectsComposer(final IRoom room) {
        super(ComposerHeaders.RoomWallObjectsComposer);

        appendInt(0);
        appendInt(0);
    }
}
