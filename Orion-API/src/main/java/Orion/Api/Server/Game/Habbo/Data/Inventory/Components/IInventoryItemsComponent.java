package Orion.Api.Server.Game.Habbo.Data.Inventory.Components;

import Orion.Api.Server.Game.Habbo.Data.Inventory.IHabboInventoryItem;

import java.util.Collection;

public interface IInventoryItemsComponent {
    Collection<IHabboInventoryItem> getItems();
}
