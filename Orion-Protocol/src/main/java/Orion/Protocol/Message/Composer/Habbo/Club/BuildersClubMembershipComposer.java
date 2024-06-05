package Orion.Protocol.Message.Composer.Habbo.Club;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class BuildersClubMembershipComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.BuildersClubMembershipComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(Integer.MAX_VALUE);
        msg.appendInt(0);
        msg.appendInt(100);
        msg.appendInt(Integer.MAX_VALUE);
        msg.appendInt(0);
    }
}
