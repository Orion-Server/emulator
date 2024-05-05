package Orion.Protocol.Message.Composer.Habbo.Inventory;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class FigureSetIdsComposer extends MessageComposer {
    public FigureSetIdsComposer(final IHabbo habbo) {
        super(ComposerHeaders.FigureSetIdsComposer);

        appendInt(0); // TODO: Implement
        appendInt(0);
    }
}
