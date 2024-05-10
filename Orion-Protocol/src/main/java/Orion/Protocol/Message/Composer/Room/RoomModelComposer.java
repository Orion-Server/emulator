package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Server.Game.Room.IRoom;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomModelComposer extends MessageComposer {
    public RoomModelComposer(
            final IRoom room
    ) {
        super(ComposerHeaders.RoomModelComposer);

        appendString(room.getData().getModel());
        appendInt(room.getData().getId());
    }
}
