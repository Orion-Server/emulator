package Orion.Protocol.Message.Composer.Navigator.Search;

import Orion.Api.Server.Game.Navigator.Data.INavigatorResultCategory;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.List;

public class NavigatorSearchResultsComposer extends MessageComposer {
    public NavigatorSearchResultsComposer(
            String code,
            String query,
            List<INavigatorResultCategory> categories
    ) {
        super(ComposerHeaders.NavigatorSearchResultsComposer);

        appendString(code);
        appendString(query);

        appendInt(categories.size());

        for (final INavigatorResultCategory category : categories) {
            category.write(this);
        }
    }
}
