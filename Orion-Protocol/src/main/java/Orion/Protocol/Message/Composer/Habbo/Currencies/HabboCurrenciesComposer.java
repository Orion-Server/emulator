package Orion.Protocol.Message.Composer.Habbo.Currencies;

import Orion.Api.Server.Game.Enums.CurrencyType;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.Arrays;
import java.util.List;

public class HabboCurrenciesComposer extends MessageComposer {
    public HabboCurrenciesComposer(
            final IHabbo habbo,
            final String currencyTypes
    ) {
        super(ComposerHeaders.HabboCurrenciesComposer);

        final List<Integer> currencyTypesArray = Arrays.stream(currencyTypes.split(";")).map(Integer::parseInt).toList();

        appendInt(currencyTypesArray.size());

        for (final int currencyType : currencyTypesArray) {
            appendInt(currencyType);
            appendInt(habbo.getCurrencies().getAmount(
                    CurrencyType.getFromType(currencyType)
            ));
        }
    }
}
