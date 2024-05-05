package Orion.Api.Server.Game.Habbo.Data;

import Orion.Api.Server.Game.Enums.CurrencyType;
import gnu.trove.map.hash.THashMap;

public interface IHabboCurrencies {
    void setAmount(CurrencyType type, int amount);

    int getAmount(CurrencyType type);

    void addAmount(CurrencyType type, int amount);

    void removeAmount(CurrencyType type, int amount);

    THashMap<CurrencyType, Integer> getCurrencies();
}
