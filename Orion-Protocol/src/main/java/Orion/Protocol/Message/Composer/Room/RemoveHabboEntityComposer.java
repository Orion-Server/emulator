package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RemoveHabboEntityComposer extends Composer {
    private final int virtualId;

    public RemoveHabboEntityComposer(final int virtualId) {
        this.virtualId = virtualId;
    }

    @Override
    public short getId() {
        return ComposerHeaders.RemoveHabboEntityComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendString(String.valueOf(this.virtualId));
    }
}
