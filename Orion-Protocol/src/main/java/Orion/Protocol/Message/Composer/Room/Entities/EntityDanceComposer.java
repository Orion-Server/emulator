package Orion.Protocol.Message.Composer.Room.Entities;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class EntityDanceComposer extends MessageComposer {
    public EntityDanceComposer(final int virtualId, final int danceId) {
        super(ComposerHeaders.EntityDanceComposer);

        appendInt(virtualId);
        appendInt(danceId);
    }
}
