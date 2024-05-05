package Orion.Protocol.Message.Composer.Habbo.Inventory;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class InventoryAchievementsComposer extends MessageComposer {
    public InventoryAchievementsComposer() {
        super(ComposerHeaders.InventoryAchievementsComposer);

        appendInt(0);
    }
}
