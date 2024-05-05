package Orion.Protocol.Message.Composer.Habbo.Inventory;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class UpdateInventoryComposer extends MessageComposer {
    public UpdateInventoryComposer() {
        super(ComposerHeaders.UpdateInventoryComposer);
    }
}
