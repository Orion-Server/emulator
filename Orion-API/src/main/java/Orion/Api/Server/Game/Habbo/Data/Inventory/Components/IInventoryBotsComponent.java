package Orion.Api.Server.Game.Habbo.Data.Inventory.Components;

import Orion.Api.Server.Game.Habbo.Data.Inventory.IHabboInventoryBot;
import Orion.Api.Util.IDisposable;
import gnu.trove.map.hash.THashMap;

public interface IInventoryBotsComponent extends IDisposable {
    void setBots(final THashMap<Integer, IHabboInventoryBot> bots);

    THashMap<Integer, IHabboInventoryBot> getBots();
}
