package Orion.Module;

import Orion.Api.Server.Game.Navigator.INavigatorManager;
import Orion.Api.Server.Game.Navigator.INavigatorRoomsProvider;
import Orion.Api.Server.Game.Navigator.Service.INavigatorSearchService;
import Orion.Api.Storage.Repository.Navigator.INavigatorRepository;
import Orion.Game.Navigator.NavigatorManager;
import Orion.Game.Navigator.NavigatorRoomsProvider;
import Orion.Game.Navigator.Service.NavigatorSearchService;
import Orion.Storage.Repository.Navigator.NavigatorRepository;
import com.google.inject.AbstractModule;

public class NavigatorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(INavigatorManager.class).to(NavigatorManager.class);

        bind(INavigatorRepository.class).to(NavigatorRepository.class);

        bind(INavigatorRoomsProvider.class).to(NavigatorRoomsProvider.class);
        bind(INavigatorSearchService.class).to(NavigatorSearchService.class);
    }
}
