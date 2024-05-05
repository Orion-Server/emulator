package Orion.Protocol.Message.Composer.Habbo;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboRightsComposer extends MessageComposer {
    public HabboRightsComposer() {
        super(ComposerHeaders.HabboRightsComposer);

        appendInt(2);
        appendInt(1);
        appendBoolean(false);
    }
}
