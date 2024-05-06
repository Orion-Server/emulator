package Orion.Protocol.Message.Composer.Habbo;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboProfileComposer extends MessageComposer {
    public HabboProfileComposer(final IHabbo habbo) {
        super(ComposerHeaders.HabboProfileComposer);

        appendInt(0);
        appendInt(0);
    }
}
