package Orion.Protocol.Message.Composer.Handshake;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class NotificationsEnabledComposer extends Composer {
    private final IEmulatorDatabaseSettings settings;

    public NotificationsEnabledComposer(final IEmulatorDatabaseSettings settings) {
        this.settings = settings;
    }

    @Override
    public short getId() {
        return ComposerHeaders.NotificationsEnabledComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendBoolean(settings.getBooleanOrDefault("bubblealerts.enabled", true));
    }
}
