package Orion.Protocol.Message.Composer.HotelView;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HotelViewCampaignComposer extends MessageComposer {
    public HotelViewCampaignComposer(
            final String campaignString,
            final String campaignName
    ) {
        super(ComposerHeaders.HotelViewCampaignComposer);

        appendString(campaignString);
        appendString(campaignName);
    }
}
