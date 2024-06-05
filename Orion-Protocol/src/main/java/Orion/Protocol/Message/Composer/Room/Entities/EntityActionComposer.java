package Orion.Protocol.Message.Composer.Room.Entities;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Object.Entity.Enum.EntityActionType;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class EntityActionComposer extends Composer {
    private final int virtualId;
    private final EntityActionType actionType;

    public EntityActionComposer(final int virtualId, final EntityActionType actionType) {
        this.virtualId = virtualId;
        this.actionType = actionType;
    }

    @Override
    public short getId() {
        return ComposerHeaders.EntityActionComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.virtualId);
        msg.appendInt(this.actionType.get());
    }
}
