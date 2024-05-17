package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Server.Game.Room.IRoom;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomHeightmapComposer extends MessageComposer {
    public RoomHeightmapComposer(final IRoom room) {
        super(ComposerHeaders.RoomHeightmapComposer);

        appendBoolean(true);
        appendInt(room.getData().getWallHeight());
        appendString(room.getModel().getData().getRelativeHeightMap());
    }
}
