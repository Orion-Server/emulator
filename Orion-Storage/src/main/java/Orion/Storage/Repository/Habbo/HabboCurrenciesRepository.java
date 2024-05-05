package Orion.Storage.Repository.Habbo;

import Orion.Api.Storage.Repository.Habbo.IHabboCurrenciesRepository;
import Orion.Api.Storage.Result.IConnectionResultConsumer;
import Orion.Storage.Query.Habbo.HabboCurrency;
import Orion.Storage.Repository.DatabaseRepository;
import com.google.inject.Singleton;

@Singleton
public class HabboCurrenciesRepository extends DatabaseRepository implements IHabboCurrenciesRepository {
    public void loadHabboCurrencies(IConnectionResultConsumer consumer, int habboId) {
        this.select(HabboCurrency.SELECT_ALL_HABBO_CURRENCIES.get(), consumer, habboId);
    }
}
