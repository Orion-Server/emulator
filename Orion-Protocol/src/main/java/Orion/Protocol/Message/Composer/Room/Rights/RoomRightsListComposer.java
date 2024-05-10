package Orion.Protocol.Message.Composer.Room.Rights;

import Orion.Api.Server.Game.Room.IRoom;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomRightsListComposer extends MessageComposer {
    public RoomRightsListComposer(final IRoom room) {
        super(ComposerHeaders.RoomRightsListComposer);

        appendInt(room.getData().getId());
        appendInt(0);
    }
}
