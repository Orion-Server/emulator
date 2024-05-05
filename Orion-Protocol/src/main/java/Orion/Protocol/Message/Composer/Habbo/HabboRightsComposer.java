package Orion.Protocol.Message.Composer.Habbo;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboRightsComposer extends MessageComposer {
    public HabboRightsComposer(final IHabbo habbo) {
        super(ComposerHeaders.HabboRightsComposer);

        // TODO: Implement
        appendInt(2);
        appendInt(1);
        appendBoolean(false);
    }
}
