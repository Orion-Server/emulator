package Orion.Protocol.Message.Composer.Navigator;

import Orion.Api.Server.Game.Habbo.Data.IHabboNavigator;
import Orion.Api.Server.Game.Habbo.Data.Navigator.IHabboNavigatorSearch;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.List;

public class NavigatorMetaDataComposer extends MessageComposer {
    private final String[] tabs = {
            "official_view",
            "hotel_view",
            "roomads_view",
            "myworld_view"
    };

    public NavigatorMetaDataComposer(final IHabboNavigator navigator) {
        super(ComposerHeaders.NavigatorMetaDataComposer);

        appendInt(this.tabs.length);

        for (String tabName : this.tabs) {
            appendString(tabName);

            final List<IHabboNavigatorSearch> savedSearches = navigator.getNavigatorSearchForTab(tabName);

            appendInt(savedSearches.size());

            for (final IHabboNavigatorSearch search : savedSearches) {
                search.write(this);
            }
        }
    }
}