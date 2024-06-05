package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomThicknessComposer extends Composer {
    private final IRoom room;

    public RoomThicknessComposer(final IRoom room) {
        this.room = room;
    }

    @Override
    public short getId() {
        return ComposerHeaders.RoomThicknessComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendBoolean(this.room.getData().isHideWall());
        msg.appendInt(this.room.getData().getThicknessWall());
        msg.appendInt(this.room.getData().getThicknessFloor());
    }
}
