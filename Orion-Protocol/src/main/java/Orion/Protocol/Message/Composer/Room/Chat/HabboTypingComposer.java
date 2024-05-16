package Orion.Protocol.Message.Composer.Room.Chat;

import Orion.Api.Server.Game.Room.Object.Entity.Type.IHabboEntity;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboTypingComposer extends MessageComposer {
    public HabboTypingComposer(final IHabboEntity entity, final boolean typing) {
        super(ComposerHeaders.HabboTypingComposer);

        appendInt(entity.getVirtualId());
        appendInt(typing ? 1 : 0);
    }
}
