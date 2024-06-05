package Orion.Protocol.Message.Composer.Room.Chat;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Object.Entity.Type.IHabboEntity;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboTypingComposer extends Composer {
    private final IHabboEntity entity;
    private final boolean typing;

    public HabboTypingComposer(final IHabboEntity entity, final boolean typing) {
        this.entity = entity;
        this.typing = typing;
    }

    @Override
    public short getId() {
        return ComposerHeaders.HabboTypingComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.entity.getVirtualId());
        msg.appendInt(this.typing ? 1 : 0);
    }
}
