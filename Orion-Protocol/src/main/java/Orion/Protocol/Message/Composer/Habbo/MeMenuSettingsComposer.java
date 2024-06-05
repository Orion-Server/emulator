package Orion.Protocol.Message.Composer.Habbo;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class MeMenuSettingsComposer extends Composer {
    private final IHabbo habbo;

    public MeMenuSettingsComposer(final IHabbo habbo) {
        this.habbo = habbo;
    }

    @Override
    public short getId() {
        return ComposerHeaders.MeMenuSettingsComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.habbo.getSettings().getSystemVolume());
        msg.appendInt(this.habbo.getSettings().getFurnitureVolume());
        msg.appendInt(this.habbo.getSettings().getTraxVolume());

        msg.appendBoolean(this.habbo.getSettings().isPreferOldChat());
        msg.appendBoolean(this.habbo.getSettings().isBlockRoomInvites());
        msg.appendBoolean(this.habbo.getSettings().isBlockCameraFollow());

        msg.appendInt(this.habbo.getSettings().getUiFlags());
        msg.appendInt(this.habbo.getSettings().getChatColor());
    }
}
