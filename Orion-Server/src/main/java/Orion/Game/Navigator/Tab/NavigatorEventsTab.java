package Orion.Game.Navigator.Tab;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Navigator.Data.INavigatorFilterType;
import Orion.Api.Server.Game.Navigator.Data.INavigatorResultCategory;
import Orion.Api.Server.Game.Navigator.Tabs.INavigatorTab;
import Orion.Api.Server.Game.Room.Data.IRoomCategory;

import java.util.ArrayList;
import java.util.List;

public class NavigatorEventsTab implements INavigatorTab {
    public final static String FILTER_NAME = "roomads_view";

    public List<INavigatorResultCategory> getResultForHabbo(IHabbo habbo) {
        // TODO: To make this class work, you need to implement the room promotion system
        return new ArrayList<>();
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

