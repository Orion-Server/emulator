package Orion.Protocol.Message.Composer.Habbo.Inventory;

import Orion.Api.Server.Game.Habbo.Data.Inventory.IHabboInventoryItem;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.Collection;

public class InventoryItemsComposer extends MessageComposer {
    public InventoryItemsComposer(
            final int pageIndex,
            final int totalPagesCount,
            final Collection<IHabboInventoryItem> items
    ) {
        super(ComposerHeaders.InventoryItemsComposer);

        appendInt(totalPagesCount);
        appendInt(pageIndex);
        appendInt(items.size());

        for(final IHabboInventoryItem item : items) {
            item.write(this);
        }
    }
}
