package Orion.Protocol.Message.Composer.Habbo.Inventory;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class InventoryEffectsListComposer extends MessageComposer {
    public InventoryEffectsListComposer(final IHabbo habbo) {
        super(ComposerHeaders.InventoryEffectsListComposer);

        appendInt(0); // TODO: Implement
    }
}
