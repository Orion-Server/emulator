package Orion.Api.Server.Game.Habbo.Data.Inventory.Components;

import Orion.Api.Server.Game.Habbo.Data.Inventory.IHabboInventoryItem;
import Orion.Api.Util.IDisposable;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public interface IInventoryItemsComponent extends IDisposable {
    Collection<IHabboInventoryItem> getItems();

    void setItems(ConcurrentHashMap<Long, IHabboInventoryItem> items);
}
