package Orion.Api.Server.Game.Habbo.Data.Inventory.Components;

import Orion.Api.Server.Game.Habbo.Data.Inventory.IHabboInventoryItem;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public interface IInventoryItemsComponent {
    Collection<IHabboInventoryItem> getItems();

    void setItems(ConcurrentHashMap<Long, IHabboInventoryItem> items);
}
