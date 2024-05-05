package Orion.Module;

import Orion.Api.Server.Game.Achievement.IAchievementManager;
import Orion.Api.Storage.Connector.IConnector;
import Orion.Api.Storage.IConnection;
import Orion.Api.Storage.IConnectionContext;
import Orion.Api.Storage.Provider.IConnectionProvider;
import Orion.Api.Storage.Repository.Emulator.IEmulatorRepository;
import Orion.Api.Storage.Repository.Habbo.IHabboCurrenciesRepository;
import Orion.Api.Storage.Repository.Habbo.IHabboNavigatorRepository;
import Orion.Api.Storage.Repository.Habbo.IHabboRepository;
import Orion.Api.Storage.Repository.Habbo.IHabboRoomsRepository;
import Orion.Api.Storage.Repository.Permission.IPermissionRepository;
import Orion.Game.Achievement.AchievementManager;
import Orion.Storage.Connection;
import Orion.Storage.ConnectionContext;
import Orion.Storage.Connector.HikariConnector;
import Orion.Storage.Provider.ConnectionProvider;
import Orion.Storage.Repository.Emulator.EmulatorRepository;
import Orion.Storage.Repository.Habbo.HabboCurrenciesRepository;
import Orion.Storage.Repository.Habbo.HabboNavigatorRepository;
import Orion.Storage.Repository.Habbo.HabboRepository;
import Orion.Storage.Repository.Habbo.HabboRoomsRepository;
import Orion.Storage.Repository.Permission.PermissionRepository;
import com.google.inject.AbstractModule;

public class AchievementModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IAchievementManager.class).to(AchievementManager.class);
    }
}
