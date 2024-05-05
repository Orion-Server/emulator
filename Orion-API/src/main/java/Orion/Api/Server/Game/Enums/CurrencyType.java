package Orion.Api.Server.Game.Enums;

import java.util.Arrays;
import java.util.List;

public enum CurrencyType {
    CREDITS(-1),
    DUCKETS(0),
    DIAMONDS(5),
    SEASONAL(101)

    ;

    private final int id;

    CurrencyType(int id) {
        this.id = id;
    }

    public int get() {
        return this.id;
    }

    public static CurrencyType getFromType(int id) {
        return switch (id) {
            case -1 -> CREDITS;
            case 0 -> DUCKETS;
            case 5 -> DIAMONDS;
            case 101 -> SEASONAL;
            default -> null;
        };
    }

    public static List<CurrencyType> getCurrencies() {
        return Arrays.stream(CurrencyType.values()).filter(currencyType ->
                currencyType != CurrencyType.CREDITS
        ).toList();
    }
}
