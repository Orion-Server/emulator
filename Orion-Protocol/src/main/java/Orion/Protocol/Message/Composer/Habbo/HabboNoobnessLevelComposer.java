package Orion.Protocol.Message.Composer.Habbo;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboNoobnessLevelComposer extends MessageComposer {
    public HabboNoobnessLevelComposer() {
        super(ComposerHeaders.HabboNoobnessLevelComposer);

        appendInt(1);
    }
}
