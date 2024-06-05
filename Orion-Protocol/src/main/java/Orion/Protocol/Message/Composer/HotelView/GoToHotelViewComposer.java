package Orion.Protocol.Message.Composer.HotelView;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class GoToHotelViewComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.GoToHotelViewMessageComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {

    }
}
