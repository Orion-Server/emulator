package Orion.Protocol.Message.Composer.Habbo;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboHomeRoomComposer extends MessageComposer {
    public HabboHomeRoomComposer(final IHabbo habbo) {
        super(ComposerHeaders.HabboHomeRoomComposer);

        appendInt(0);
        appendInt(0);
    }
}
