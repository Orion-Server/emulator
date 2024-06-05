package Orion.Protocol.Message.Composer.Room.Access;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomAccessDeniedComposer extends Composer {
    private final String username;

    public RoomAccessDeniedComposer(final String username) {
        this.username = username;
    }

    @Override
    public short getId() {
        return ComposerHeaders.RoomAccessDeniedComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendString(this.username);
    }
}
