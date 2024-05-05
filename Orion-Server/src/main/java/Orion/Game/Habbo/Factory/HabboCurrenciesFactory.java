package Orion.Game.Habbo.Factory;

import Orion.Api.Server.Game.Enums.CurrencyType;
import Orion.Api.Server.Game.Habbo.Data.IHabboCurrencies;
import Orion.Api.Storage.Repository.Habbo.IHabboCurrenciesRepository;
import Orion.Game.Habbo.Data.HabboCurrencies;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class HabboCurrenciesFactory {
    @Inject
    private IHabboCurrenciesRepository currenciesRepository;

    public IHabboCurrencies create(final int habboId) {
        final IHabboCurrencies currencies = new HabboCurrencies();

        this.currenciesRepository.loadHabboCurrencies(result -> {
            if(result == null) return;

            final CurrencyType type = CurrencyType.getFromType(result.getInt("type"));

            if(type == null) {
                System.out.println(STR."Invalid currency type: \{result.getInt("type")}");
                return;
            }

            currencies.setAmount(type, result.getInt("amount"));
        }, habboId);

        return currencies;
    }
}
