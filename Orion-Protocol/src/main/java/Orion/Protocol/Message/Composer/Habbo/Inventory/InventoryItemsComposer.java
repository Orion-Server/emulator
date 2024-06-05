package Orion.Protocol.Message.Composer.Habbo.Inventory;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.Data.Inventory.IHabboInventoryItem;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.Collection;

public class InventoryItemsComposer extends Composer {
    private final int pageIndex;
    private final int totalPagesCount;
    private final Collection<IHabboInventoryItem> items;

    public InventoryItemsComposer(
            final int pageIndex,
            final int totalPagesCount,
            final Collection<IHabboInventoryItem> items
    ) {
        this.pageIndex = pageIndex;
        this.totalPagesCount = totalPagesCount;
        this.items = items;
    }

    @Override
    public short getId() {
        return ComposerHeaders.InventoryItemsComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(totalPagesCount);
        msg.appendInt(pageIndex);
        msg.appendInt(items.size());

        for(final IHabboInventoryItem item : items) {
            item.write(msg);
        }
    }
}
