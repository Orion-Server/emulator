package Orion.Protocol.Message.Composer.Room.Access;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class AddHabboToDoorbellComposer extends Composer {
    private final String username;

    public AddHabboToDoorbellComposer(final String username) {
        this.username = username;
    }

    @Override
    public short getId() {
        return ComposerHeaders.AddHabboToDoorbellComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendString(this.username);
    }
}
