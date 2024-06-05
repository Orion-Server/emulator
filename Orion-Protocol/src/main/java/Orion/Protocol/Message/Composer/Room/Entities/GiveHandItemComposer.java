package Orion.Protocol.Message.Composer.Room.Entities;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class GiveHandItemComposer extends Composer {
    private final int entityId;
    private final int handItemId;

    public GiveHandItemComposer(final int entityId, final int handItemId) {
        this.entityId = entityId;
        this.handItemId = handItemId;
    }

    @Override
    public short getId() {
        return ComposerHeaders.GiveHandItemComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.entityId);
        msg.appendInt(this.handItemId);
    }
}
