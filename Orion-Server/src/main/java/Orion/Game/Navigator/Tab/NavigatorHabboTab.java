package Orion.Game.Navigator.Tab;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Navigator.Data.INavigatorFilterType;
import Orion.Api.Server.Game.Navigator.Data.INavigatorResultCategory;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorDisplayOrder;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorListAction;
import Orion.Api.Server.Game.Navigator.INavigatorRoomsProvider;
import Orion.Api.Server.Game.Navigator.Tabs.INavigatorTab;
import Orion.Api.Server.Game.Room.Data.IRoomCategory;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Game.Navigator.Data.NavigatorResultCategory;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class NavigatorHabboTab implements INavigatorTab {
    public final static String FILTER_NAME = "myworld_view";

    @Inject
    private INavigatorRoomsProvider navigatorRoomsProvider;

    private final String[] categories = {
            "my",
            "favorites",
            "history_freq",
            "my_groups",
            "with_friends",
            "with_rights"
    };

    public List<INavigatorResultCategory> getResultForHabbo(IHabbo habbo) {
        int order = 0;
        final List<INavigatorResultCategory> categories = new ArrayList<>();

        for(String category : this.categories) {
            final List<IRoom> categoryRooms = this.navigatorRoomsProvider.getRoomsForCategory(category, habbo);

            categories.add(new NavigatorResultCategory(
                    order,
                    category,
                    "",
                    NavigatorListAction.NONE,
                    habbo.getNavigator().getDisplayModeForCategory(category),
                    habbo.getNavigator().getLayoutDisplayForCategory(category),
                    categoryRooms,
                    true,
                    true, // TODO: Should I show invisible rooms for all categories?
                    NavigatorDisplayOrder.ORDER_NUMERICAL,
                    order
            ));

            order++;
        }

        return categories;
    }

    @Override
    public List<INavigatorResultCategory> getSearchedResultForHabbo(
            IHabbo habbo,
            INavigatorFilterType filterType,
            String search,
            IRoomCategory category
    ) {
        return this.getResultForHabbo(habbo).stream()
                .filter(result -> result.filterRooms(filterType, search))
                .toList();
    }
}
