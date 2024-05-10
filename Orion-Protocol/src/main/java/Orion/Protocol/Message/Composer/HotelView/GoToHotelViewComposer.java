package Orion.Protocol.Message.Composer.HotelView;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class GoToHotelViewComposer extends MessageComposer {
    public GoToHotelViewComposer() {
        super(ComposerHeaders.GoToHotelViewMessageComposer);
    }
}
