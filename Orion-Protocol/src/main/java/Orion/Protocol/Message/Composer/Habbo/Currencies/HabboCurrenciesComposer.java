package Orion.Protocol.Message.Composer.Habbo.Currencies;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Enums.CurrencyType;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.Arrays;
import java.util.List;

public class HabboCurrenciesComposer extends Composer {
    private final IHabbo habbo;
    private final String currencyTypes;

    public HabboCurrenciesComposer(final IHabbo habbo, final String currencyTypes) {
        this.habbo = habbo;
        this.currencyTypes = currencyTypes;
    }

    @Override
    public short getId() {
        return ComposerHeaders.HabboCurrenciesComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        final List<Integer> currencyTypesArray = Arrays.stream(this.currencyTypes.split(";")).map(Integer::parseInt).toList();

        msg.appendInt(currencyTypesArray.size());

        for (final int currencyType : currencyTypesArray) {
            msg.appendInt(currencyType);
            msg.appendInt(this.habbo.getCurrencies().getAmount(
                    CurrencyType.getFromType(currencyType)
            ));
        }
    }
}
