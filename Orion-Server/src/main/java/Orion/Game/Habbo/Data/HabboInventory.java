package Orion.Game.Habbo.Data;

import Orion.Api.Server.Game.Habbo.Data.IHabboInventory;
import Orion.Api.Server.Game.Habbo.Data.Inventory.Components.IInventoryBotsComponent;
import Orion.Api.Server.Game.Habbo.Data.Inventory.Components.IInventoryItemsComponent;

public class HabboInventory implements IHabboInventory {
    private final IInventoryItemsComponent itemsComponent;

    private final IInventoryBotsComponent botsComponent;

    public HabboInventory(
            final IInventoryItemsComponent itemsComponent,
            final IInventoryBotsComponent botsComponent
            ) {
        this.itemsComponent = itemsComponent;
        this.botsComponent = botsComponent;
    }

    @Override
    public IInventoryItemsComponent getItemsComponent() {
        return this.itemsComponent;
    }

    @Override
    public IInventoryBotsComponent getBotsComponent() {
        return this.botsComponent;
    }

    @Override
    public void dispose() {
        this.itemsComponent.dispose();
        this.botsComponent.dispose();
    }
}
