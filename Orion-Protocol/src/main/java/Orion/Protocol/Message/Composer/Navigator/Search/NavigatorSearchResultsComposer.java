package Orion.Protocol.Message.Composer.Navigator.Search;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Navigator.Data.INavigatorResultCategory;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.List;

public class NavigatorSearchResultsComposer extends Composer {
    private final String code;
    private final String query;
    private final List<INavigatorResultCategory> categories;

    public NavigatorSearchResultsComposer(
            final String code,
            final String query,
            final List<INavigatorResultCategory> categories
    ) {
        this.code = code;
        this.query = query;
        this.categories = categories;
    }

    @Override
    public short getId() {
        return ComposerHeaders.NavigatorSearchResultsComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendString(code);
        msg.appendString(query);

        msg.appendInt(categories.size());

        for (final INavigatorResultCategory category : categories) {
            category.write(msg);
        }
    }
}
