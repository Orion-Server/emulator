package Orion.Protocol.Message.Composer.Handshake;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class AuthenticationOkComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.AuthenticationOkComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {

    }
}
