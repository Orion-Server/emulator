package Orion.Game.Navigator.Tab;

import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Navigator.Data.INavigatorFilterType;
import Orion.Api.Server.Game.Navigator.Data.INavigatorResultCategory;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorDisplayMode;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorDisplayOrder;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorListAction;
import Orion.Api.Server.Game.Navigator.INavigatorManager;
import Orion.Api.Server.Game.Navigator.INavigatorRoomsProvider;
import Orion.Api.Server.Game.Navigator.Tabs.INavigatorTab;
import Orion.Api.Server.Game.Room.Data.IRoomCategory;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Game.Navigator.Data.NavigatorResultCategory;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NavigatorRecommendedTab implements INavigatorTab {
    public final static String FILTER_NAME = "hotel_view";

    private final String category = "popular";

    @Inject
    private INavigatorRoomsProvider navigatorRoomsProvider;

    @Inject
    private INavigatorManager navigatorManager;

    @Inject
    private IEmulatorDatabaseSettings databaseSettings;

    public List<INavigatorResultCategory> getResultForHabbo(IHabbo habbo) {
        boolean showInvisible = false; // TODO: Implement permissions

        int order = 0;
        final List<INavigatorResultCategory> categories = new ArrayList<>();

        categories.add(new NavigatorResultCategory(
                order,
                this.category,
                "",
                NavigatorListAction.NONE,
                habbo.getNavigator().getDisplayModeForCategory(this.category, this.getDefaultDisplayMode()),
                habbo.getNavigator().getLayoutDisplayForCategory(this.category),
                this.navigatorRoomsProvider.getRoomsForCategory(this.category, habbo),
                false,
                showInvisible,
                NavigatorDisplayOrder.ORDER_NUMERICAL,
                -1
        ));

        final Map<IRoomCategory, List<IRoom>> roomsByCategory = this.navigatorRoomsProvider.getRoomsFromCategories(habbo);

        for (final Map.Entry<IRoomCategory, List<IRoom>> entry : roomsByCategory.entrySet()) {
            categories.add(new NavigatorResultCategory(
                    ++order,
                    entry.getKey().getCaption(),
                    entry.getKey().getCaption(),
                    NavigatorListAction.MORE,
                    habbo.getNavigator().getDisplayModeForCategory(entry.getKey().getCaptionSave()),
                    habbo.getNavigator().getLayoutDisplayForCategory(entry.getKey().getCaptionSave()),
                    entry.getValue(),
                    true,
                    showInvisible,
                    NavigatorDisplayOrder.ORDER_NUMERICAL,
                    entry.getKey().getOrder()
            ));
        }

        return categories;
    }

    private NavigatorDisplayMode getDefaultDisplayMode() {
        final String displayMode = this.databaseSettings.getSettingOrDefault("hotel.navigator.popular.listtype", "0");

        try {
            return NavigatorDisplayMode.fromType(Integer.parseInt(displayMode));
        } catch (NumberFormatException e) {
            return NavigatorDisplayMode.LIST;
        }
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
