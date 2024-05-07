package Orion.Game.Navigator.Tab;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Navigator.Data.INavigatorFilterType;
import Orion.Api.Server.Game.Navigator.Data.INavigatorPublicCategory;
import Orion.Api.Server.Game.Navigator.Data.INavigatorResultCategory;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorDisplayMode;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorDisplayOrder;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorListAction;
import Orion.Api.Server.Game.Navigator.INavigatorManager;
import Orion.Api.Server.Game.Navigator.INavigatorRoomsProvider;
import Orion.Api.Server.Game.Navigator.Tabs.INavigatorTab;
import Orion.Api.Server.Game.Room.Data.IRoomCategory;
import Orion.Game.Navigator.Data.NavigatorResultCategory;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class NavigatorOfficialTab implements INavigatorTab {
    public final static String FILTER_NAME = "official_view";

    private final String category = "official-root";

    @Inject
    private INavigatorRoomsProvider navigatorRoomsProvider;

    @Inject
    private INavigatorManager navigatorManager;

    public List<INavigatorResultCategory> getResultForHabbo(IHabbo habbo) {
        boolean showInvisible = false; // TODO: Implement permissions

        int order = 0;
        final List<INavigatorResultCategory> categories = new ArrayList<>();

        categories.add(new NavigatorResultCategory(
                order,
                this.category,
                "",
                NavigatorListAction.NONE,
                habbo.getNavigator().getDisplayModeForCategory(this.category, NavigatorDisplayMode.THUMBNAILS),
                habbo.getNavigator().getLayoutDisplayForCategory(this.category),
                this.navigatorRoomsProvider.getRoomsForCategory(this.category, habbo),
                false,
                showInvisible,
                NavigatorDisplayOrder.ORDER_NUMERICAL,
                -1
        ));

        for (final INavigatorPublicCategory publicCategory : this.navigatorManager.getPublicCategories().values()) {
            categories.add(new NavigatorResultCategory(
                    ++order,
                    "",
                    publicCategory.getName(),
                    NavigatorListAction.NONE,
                    habbo.getNavigator().getDisplayModeForCategory(publicCategory.getName(), publicCategory.getDisplayMode()),
                    habbo.getNavigator().getLayoutDisplayForCategory(publicCategory.getName()),
                    publicCategory.getRooms(),
                    true,
                    showInvisible,
                    NavigatorDisplayOrder.ORDER_NUMERICAL,
                    publicCategory.getOrder()
            ));
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

