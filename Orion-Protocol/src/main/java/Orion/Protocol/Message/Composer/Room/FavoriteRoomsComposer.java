package Orion.Protocol.Message.Composer.Room;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class FavoriteRoomsComposer extends MessageComposer {
    public FavoriteRoomsComposer() {
        super(ComposerHeaders.FavoriteRoomsComposer);

        appendInt(30);
        appendInt(0);
    }
}
