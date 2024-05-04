package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class CampaignCalendarDataComposer extends MessageComposer {
    public CampaignCalendarDataComposer() {
        super(ComposerHeaders.CampaignCalendarDataComposer);

        appendString("xmas14");
        appendString("");
        appendInt(0);
        appendInt(0);
        appendInt(0);
        appendInt(0);
    }
}
