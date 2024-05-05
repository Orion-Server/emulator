package Orion.Protocol.Message.Composer.Habbo;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class MeMenuSettingsComposer extends MessageComposer {
    public MeMenuSettingsComposer(final IHabbo habbo) {
        super(ComposerHeaders.MeMenuSettingsComposer);

        appendInt(habbo.getSettings().getSystemVolume());
        appendInt(habbo.getSettings().getFurnitureVolume());
        appendInt(habbo.getSettings().getTraxVolume());

        appendBoolean(habbo.getSettings().isPreferOldChat());
        appendBoolean(habbo.getSettings().isBlockRoomInvites());
        appendBoolean(habbo.getSettings().isBlockCameraFollow());

        appendInt(habbo.getSettings().getUiFlags());
        appendInt(habbo.getSettings().getChatColor());
    }
}
