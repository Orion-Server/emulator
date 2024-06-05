package Orion.Protocol.Message.Composer.Habbo.Inventory;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class UpdateInventoryComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.UpdateInventoryComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        // TODO: Implement
    }
}
