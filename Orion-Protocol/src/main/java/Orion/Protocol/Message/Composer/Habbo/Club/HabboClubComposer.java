package Orion.Protocol.Message.Composer.Habbo.Club;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboClubComposer extends MessageComposer {
    public HabboClubComposer() {
        super(ComposerHeaders.HabboClubComposer);

        // TODO: Implement
        appendString("HABBO_CLUB".toLowerCase());
        appendInt(0);
        appendInt(0);
        appendInt(0);
        appendInt(0);
        appendBoolean(false);
        appendBoolean(false);
        appendInt(0);
        appendInt(0);
        appendInt(0);
        appendInt(0);
    }
}
