package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomHeightmapComposer extends Composer {
    private final IRoom room;

    public RoomHeightmapComposer(final IRoom room) {
        this.room = room;
    }

    @Override
    public short getId() {
        return ComposerHeaders.RoomHeightmapComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendBoolean(true);
        msg.appendInt(this.room.getData().getWallHeight());
        msg.appendString(this.room.getModel().getData().getRelativeHeightMap());
    }
}
