package Orion.Game.Habbo.Data.Inventory.Components;

import Orion.Api.Server.Game.Habbo.Data.Inventory.Components.IInventoryBotsComponent;
import Orion.Api.Server.Game.Habbo.Data.Inventory.IHabboInventoryBot;
import gnu.trove.map.hash.THashMap;

public class InventoryBotsComponent implements IInventoryBotsComponent {
    private final THashMap<Integer, IHabboInventoryBot> bots;

    public InventoryBotsComponent() {
        this.bots = new THashMap<>();
    }

    @Override
    public void setBots(final THashMap<Integer, IHabboInventoryBot> bots) {
        this.bots.putAll(bots);
    }

    @Override
    public THashMap<Integer, IHabboInventoryBot> getBots() {
        return this.bots;
    }

    @Override
    public void dispose() {
        this.bots.clear();
    }
}
