package Orion.Api.Storage.Repository.Habbo;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface IHabboCurrenciesRepository {
    void loadHabboCurrencies(IConnectionResultConsumer consumer, int habboId);
}
