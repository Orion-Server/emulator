package Orion.Protocol.Message.Composer.Room.Entities;

import Orion.Api.Server.Game.Room.Object.Entity.Enum.EntityActionType;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class EntityActionComposer extends MessageComposer {
    public EntityActionComposer(final int virtualId, final EntityActionType actionType) {
        super(ComposerHeaders.EntityActionComposer);

        appendInt(virtualId);
        appendInt(actionType.get());
    }
}
