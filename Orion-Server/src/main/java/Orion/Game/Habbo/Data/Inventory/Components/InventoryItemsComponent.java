package Orion.Game.Habbo.Data.Inventory.Components;

import Orion.Api.Server.Game.Habbo.Data.Inventory.Components.IInventoryItemsComponent;
import Orion.Api.Server.Game.Habbo.Data.Inventory.IHabboInventoryItem;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryItemsComponent implements IInventoryItemsComponent {
    private final ConcurrentHashMap<Long, IHabboInventoryItem> items;

    public InventoryItemsComponent(ConcurrentHashMap<Long, IHabboInventoryItem> items) {
        this.items = items;
    }

    @Override
    public Collection<IHabboInventoryItem> getItems() {
        return this.items.values();
    }

    @Override
    public void setItems(ConcurrentHashMap<Long, IHabboInventoryItem> items) {
        this.items.putAll(items);
    }
}
