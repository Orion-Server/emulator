package Orion.Game.Navigator.Service;

import Orion.Api.Server.Core.Configuration.IEmulatorEnvironmentSettings;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Navigator.Data.INavigatorFilterType;
import Orion.Api.Server.Game.Navigator.Data.INavigatorResultCategory;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorDisplayMode;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorDisplayOrder;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorLayoutDisplay;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorListAction;
import Orion.Api.Server.Game.Navigator.INavigatorManager;
import Orion.Api.Server.Game.Navigator.INavigatorRoomsProvider;
import Orion.Api.Server.Game.Navigator.Service.INavigatorSearchService;
import Orion.Api.Server.Game.Navigator.Tabs.INavigatorTab;
import Orion.Api.Server.Game.Room.Data.IRoomCategory;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.IRoomManager;
import Orion.Game.Navigator.Data.NavigatorResultCategory;
import Orion.Protocol.Message.Composer.Navigator.Search.NavigatorSearchResultsComposer;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Singleton
public class NavigatorSearchService implements INavigatorSearchService {
    private final Executor executor;

    private final INavigatorManager navigatorManager;

    private final IRoomManager roomManager;

    private final INavigatorRoomsProvider navigatorRoomsProvider;

    @Inject
    public NavigatorSearchService(
            final IEmulatorEnvironmentSettings environmentSettings,
            final INavigatorManager navigatorManager,
            final INavigatorRoomsProvider navigatorRoomsProvider,
            final IRoomManager roomManager
    ) {
        this.roomManager = roomManager;
        this.navigatorManager = navigatorManager;
        this.navigatorRoomsProvider = navigatorRoomsProvider;

        this.executor = Executors.newScheduledThreadPool(
                environmentSettings.getIntegerOrDefault("game.hotel.navigator_search.threads", 2)
        );
    }

    public void commit(IHabbo habbo, String tabName, String query) {
        this.executor.execute(() -> {
            final INavigatorTab tab = this.navigatorManager.getTab(tabName);

            if(tab == null) {
                this.sendRoomsFromCategory(habbo, tabName, query);
                return;
            }

            if(query.isBlank()) {
                habbo.getSession().send(new NavigatorSearchResultsComposer(tabName, query, tab.getResultForHabbo(habbo)));
                return;
            }

            INavigatorFilterType filterType = this.navigatorManager.getFilterTypeByKey("anything");

            if(filterType == null) return;

            final String[] parsedQuery = query.split(":");
            final IRoomCategory category = this.roomManager.getCategoryFromTab(tabName);

            if(parsedQuery.length <= 1) {
                habbo.getSession().send(new NavigatorSearchResultsComposer(
                        tabName, query, tab.getSearchedResultForHabbo(habbo, filterType, parsedQuery[0], category)
                ));
                return;
            }

            filterType = this.navigatorManager.getFilterTypeByKey(parsedQuery[0].replace(":", ""));

            if(filterType == null) return;

            habbo.getSession().send(new NavigatorSearchResultsComposer(
                    tabName, query, tab.getSearchedResultForHabbo(habbo, filterType, parsedQuery[1], category)
            ));
        });
    }

    private void sendRoomsFromCategory(IHabbo habbo, String tabName, String query) {
        final List<IRoom> rooms = this.navigatorRoomsProvider.getRoomsForCategory(tabName, habbo);

        final List<INavigatorResultCategory> categories = new ArrayList<>() {
            {
                add(new NavigatorResultCategory(0, tabName, query, NavigatorListAction.NONE, NavigatorDisplayMode.LIST, NavigatorLayoutDisplay.DEFAULT, rooms, false, false, NavigatorDisplayOrder.ACTIVITY, -1));
            }
        };

        habbo.getSession().send(new NavigatorSearchResultsComposer(tabName, query, categories));
    }
}