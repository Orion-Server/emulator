package Orion.Protocol.Message.Composer.Navigator;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class NavigatorLiftedRoomsComposer extends MessageComposer {
    public NavigatorLiftedRoomsComposer() {
        super(ComposerHeaders.NavigatorLiftedRoomsComposer);

        appendInt(0);
    }
}