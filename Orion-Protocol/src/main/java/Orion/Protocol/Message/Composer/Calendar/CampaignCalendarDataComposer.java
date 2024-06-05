package Orion.Protocol.Message.Composer.Calendar;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class CampaignCalendarDataComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.CampaignCalendarDataComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendString("xmas14");
        msg.appendString("");
        msg.appendInt(0);
        msg.appendInt(0);
        msg.appendInt(0);
        msg.appendInt(0);
    }
}
