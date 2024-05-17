package Orion.Protocol.Message.Composer.Room.Entities;

import Orion.Api.Server.Game.Habbo.Enums.HabboActionType;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class EntityActionComposer extends MessageComposer {
    public EntityActionComposer(final int virtualId, final HabboActionType actionType) {
        super(ComposerHeaders.EntityActionComposer);

        appendInt(virtualId);
        appendInt(actionType.get());
    }
}
