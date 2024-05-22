package Orion.Protocol.Message.Composer.Room.Entities;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class GiveHandItemComposer extends MessageComposer {
    public GiveHandItemComposer(final int entityId, final int handItemId) {
        super(ComposerHeaders.GiveHandItemComposer);

        appendInt(entityId);
        appendInt(handItemId);
    }
}
