package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Server.Game.Room.IRoom;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomThicknessComposer extends MessageComposer {
    public RoomThicknessComposer(final IRoom room) {
        super(ComposerHeaders.RoomThicknessComposer);

        appendBoolean(room.getData().isHideWall());
        appendInt(room.getData().getThicknessWall());
        appendInt(room.getData().getThicknessFloor());
    }
}
