package Orion.Protocol.Message.Composer.Habbo.Club;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class BuildersClubMembershipComposer extends MessageComposer {
    public BuildersClubMembershipComposer() {
        super(ComposerHeaders.BuildersClubMembershipComposer);

        appendInt(Integer.MAX_VALUE);
        appendInt(0);
        appendInt(100);
        appendInt(Integer.MAX_VALUE);
        appendInt(0);
    }
}
