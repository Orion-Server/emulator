package Orion.Api.Storage.Repository.Habbo;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface IHabboInventoryRepository {
    void loadAllHabboInventory(IConnectionResultConsumer consumer, int habboId);
}
