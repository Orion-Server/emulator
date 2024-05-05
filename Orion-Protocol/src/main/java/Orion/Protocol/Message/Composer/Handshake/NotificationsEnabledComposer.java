package Orion.Protocol.Message.Composer.Handshake;

import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class NotificationsEnabledComposer extends MessageComposer {
    public NotificationsEnabledComposer(final IEmulatorDatabaseSettings settings) {
        super(ComposerHeaders.NotificationsEnabledComposer);

        appendBoolean(settings.getBooleanOrDefault("bubblealerts.enabled", true));
    }
}
