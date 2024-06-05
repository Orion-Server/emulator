package Orion.Protocol.Message.Composer.HotelView;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HotelViewCampaignComposer extends Composer {
    private final String campaignString;
    private final String campaignName;

    public HotelViewCampaignComposer(final String campaignString, final String campaignName) {
        this.campaignString = campaignString;
        this.campaignName = campaignName;
    }

    @Override
    public short getId() {
        return ComposerHeaders.HotelViewCampaignComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendString(this.campaignString);
        msg.appendString(this.campaignName);
    }
}
