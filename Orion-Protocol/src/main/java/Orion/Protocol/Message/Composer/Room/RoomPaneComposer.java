package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Server.Game.Room.IRoom;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomPaneComposer extends MessageComposer {
    public RoomPaneComposer(
            final IRoom room,
            final boolean isOwner
    ) {
        super(ComposerHeaders.RoomPaneComposer);

        appendInt(room.getData().getId());
        appendBoolean(isOwner);
    }
}
