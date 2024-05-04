package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class FavouritesComposer extends MessageComposer {
    public FavouritesComposer() {
        super(ComposerHeaders.FavouritesComposer);

        appendInt(30);
        appendInt(0);
    }
}
