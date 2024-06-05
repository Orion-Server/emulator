package Orion.Protocol.Message.Composer.Room.Entities;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class EntityDanceComposer extends Composer {
    private final int virtualId;
    private final int danceId;

    public EntityDanceComposer(final int virtualId, final int danceId) {
        this.virtualId = virtualId;
        this.danceId = danceId;
    }

    @Override
    public short getId() {
        return ComposerHeaders.EntityDanceComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.virtualId);
        msg.appendInt(this.danceId);
    }
}
