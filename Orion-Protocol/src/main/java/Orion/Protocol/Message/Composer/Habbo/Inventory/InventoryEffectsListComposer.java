package Orion.Protocol.Message.Composer.Habbo.Inventory;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class InventoryEffectsListComposer extends MessageComposer {
    public InventoryEffectsListComposer() {
        super(ComposerHeaders.InventoryEffectsListComposer);

        appendInt(0);
    }
}
