package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class InfoFeedEnableComposer extends MessageComposer {
    public InfoFeedEnableComposer() {
        super(ComposerHeaders.InfoFeedEnableComposer);

        appendBoolean(true);
    }
}
