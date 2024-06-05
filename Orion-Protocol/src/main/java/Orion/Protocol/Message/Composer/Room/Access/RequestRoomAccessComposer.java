package Orion.Protocol.Message.Composer.Room.Access;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RequestRoomAccessComposer extends Composer {
    private final String username;

    public RequestRoomAccessComposer(final String username) {
        this.username = username;
    }

    @Override
    public short getId() {
        return ComposerHeaders.RequestRoomAccessComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendString(this.username);
    }
}
