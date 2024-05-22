package Orion.Module;

import Orion.Api.Server.Game.Habbo.Factory.IHabboInventoryFactory;
import Orion.Api.Server.Game.Habbo.IHabboManager;
import Orion.Api.Server.Game.Habbo.Provider.IHabboLoginProvider;
import Orion.Game.Habbo.Factory.*;
import Orion.Game.Habbo.HabboManager;
import Orion.Game.Habbo.Provider.HabboLoginProvider;
import com.google.inject.AbstractModule;

public class HabboModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IHabboManager.class).to(HabboManager.class);
        bind(IHabboLoginProvider.class).to(HabboLoginProvider.class);

        bind(HabboFactory.class).asEagerSingleton();
        bind(HabboRoomsFactory.class).asEagerSingleton();
        bind(HabboCurrenciesFactory.class).asEagerSingleton();
        bind(HabboNavigatorFactory.class).asEagerSingleton();
        bind(HabboAchievementsFactory.class).asEagerSingleton();
        bind(HabboMessengerFactory.class).asEagerSingleton();
        bind(IHabboInventoryFactory.class).to(HabboInventoryFactory.class);
    }
}
