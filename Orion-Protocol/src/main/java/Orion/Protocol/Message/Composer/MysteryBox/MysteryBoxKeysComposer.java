package Orion.Protocol.Message.Composer.MysteryBox;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class MysteryBoxKeysComposer extends MessageComposer {
    public MysteryBoxKeysComposer() {
        super(ComposerHeaders.MysteryBoxKeysComposer);

        appendString("");
        appendString("");
    }
}
