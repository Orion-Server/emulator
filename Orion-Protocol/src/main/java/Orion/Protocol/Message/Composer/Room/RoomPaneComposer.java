package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomPaneComposer extends Composer {
    private final IRoom room;
    private final boolean isOwner;

    public RoomPaneComposer(final IRoom room, final boolean isOwner) {
        this.room = room;
        this.isOwner = isOwner;
    }

    @Override
    public short getId() {
        return ComposerHeaders.RoomPaneComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.room.getData().getId());
        msg.appendBoolean(this.isOwner);
    }
}
