package Orion.Protocol.Message.Composer.Navigator;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.Data.IHabboNavigator;
import Orion.Api.Server.Game.Habbo.Data.Navigator.IHabboNavigatorSearch;
import Orion.Networking.Message.Composer;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.List;

public class NavigatorMetaDataComposer extends Composer {
    private final IHabboNavigator navigator;

    private final String[] tabs = {
            "official_view",
            "hotel_view",
            "roomads_view",
            "myworld_view"
    };

    public NavigatorMetaDataComposer(final IHabboNavigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public short getId() {
        return ComposerHeaders.NavigatorMetaDataComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.tabs.length);

        for (final String tabName : this.tabs) {
            msg.appendString(tabName);

            final List<IHabboNavigatorSearch> savedSearches = this.navigator.getNavigatorSearchForTab(tabName);

            msg.appendInt(savedSearches.size());

            for (final IHabboNavigatorSearch search : savedSearches) {
                search.write(msg);
            }
        }
    }
}