package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class AvatarEffectsComposer extends MessageComposer {
    public AvatarEffectsComposer() {
        super(ComposerHeaders.AvatarEffectsComposer);

        appendInt(0);
    }
}
