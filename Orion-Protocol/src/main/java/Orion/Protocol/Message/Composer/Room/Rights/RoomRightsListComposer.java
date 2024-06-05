package Orion.Protocol.Message.Composer.Room.Rights;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomRightsListComposer extends Composer {
    private final IRoom room;

    public RoomRightsListComposer(final IRoom room) {
        this.room = room;
    }

    @Override
    public short getId() {
        return ComposerHeaders.RoomRightsListComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.room.getData().getId());
        msg.appendInt(0);
    }
}
