package Orion.Protocol.Message.Composer.Habbo.Club;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboClubComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.HabboClubComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        // TODO: Implement
        msg.appendString("HABBO_CLUB".toLowerCase());
        msg.appendInt(0);
        msg.appendInt(0);
        msg.appendInt(0);
        msg.appendInt(0);
        msg.appendBoolean(false);
        msg.appendBoolean(false);
        msg.appendInt(0);
        msg.appendInt(0);
        msg.appendInt(0);
        msg.appendInt(0);
    }
}
