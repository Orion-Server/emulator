package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomCreatedComposer extends Composer {
    private final int roomId;
    private final String roomName;

    public RoomCreatedComposer(final int roomId, final String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }

    @Override
    public short getId() {
        return ComposerHeaders.RoomCreatedComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.roomId);
        msg.appendString(this.roomName);
    }
}
