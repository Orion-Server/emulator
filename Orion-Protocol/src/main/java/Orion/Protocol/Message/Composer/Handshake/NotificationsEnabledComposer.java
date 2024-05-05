package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class NotificationsEnabledComposer extends MessageComposer {
    public NotificationsEnabledComposer() {
        super(ComposerHeaders.NotificationsEnabledComposer);

        appendBoolean(true);
    }
}
