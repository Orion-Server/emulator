package Orion.Game.Habbo.Data;

import Orion.Api.Server.Game.Enums.CurrencyType;
import Orion.Api.Server.Game.Habbo.Data.IHabboCurrencies;
import gnu.trove.map.hash.THashMap;

public class HabboCurrencies implements IHabboCurrencies {
    private final THashMap<CurrencyType, Integer> currencies;

    public HabboCurrencies() {
        this.currencies = new THashMap<>();
    }

    @Override
    public void setAmount(CurrencyType type, int amount) {
        this.currencies.put(type, amount);
    }

    @Override
    public int getAmount(CurrencyType type) {
        return this.currencies.getOrDefault(type, 0);
    }

    @Override
    public void addAmount(CurrencyType type, int amount) {
        this.currencies.put(type, this.getAmount(type) + amount);
    }

    @Override
    public void removeAmount(CurrencyType type, int amount) {
        this.currencies.put(type, this.getAmount(type) - amount);
    }

    @Override
    public THashMap<CurrencyType, Integer> getCurrencies() {
        return this.currencies;
    }
}
