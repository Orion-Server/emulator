package Orion.Game.Habbo.Data;

import Orion.Api.Server.Game.Habbo.Data.IHabboInventory;
import Orion.Api.Server.Game.Habbo.Data.Inventory.Components.IInventoryItemsComponent;

public class HabboInventory implements IHabboInventory {
    private final IInventoryItemsComponent itemsComponent;

    public HabboInventory(
            final IInventoryItemsComponent itemsComponent
    ) {
        this.itemsComponent = itemsComponent;
    }

    @Override
    public IInventoryItemsComponent getItemsComponent() {
        return this.itemsComponent;
    }

    @Override
    public void dispose() {

    }
}
