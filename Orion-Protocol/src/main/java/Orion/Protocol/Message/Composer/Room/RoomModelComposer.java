package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomModelComposer extends Composer {
    private final IRoom room;

    public RoomModelComposer(final IRoom room) {
        this.room = room;
    }

    @Override
    public short getId() {
        return ComposerHeaders.RoomModelComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendString(this.room.getData().getModel());
        msg.appendInt(this.room.getData().getId());
    }
}
