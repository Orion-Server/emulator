package Orion.Protocol.Message.Composer.Navigator;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class CanCreateRoomComposer extends Composer {
    private final int canCreateRoom;
    private final int roomLimit;

    public CanCreateRoomComposer(int canCreateRoom, int roomLimit) {
        this.canCreateRoom = canCreateRoom;
        this.roomLimit = roomLimit;
    }

    @Override
    public short getId() {
        return ComposerHeaders.CanCreateRoomComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.canCreateRoom);
        msg.appendInt(this.roomLimit);
    }
}
