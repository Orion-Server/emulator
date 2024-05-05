package Orion.Module;

import Orion.Api.Storage.Connector.IConnector;
import Orion.Api.Storage.IConnection;
import Orion.Api.Storage.IConnectionContext;
import Orion.Api.Storage.Provider.IConnectionProvider;
import Orion.Api.Storage.Repository.Emulator.IEmulatorRepository;
import Orion.Api.Storage.Repository.Habbo.IHabboCurrenciesRepository;
import Orion.Api.Storage.Repository.Habbo.IHabboNavigatorRepository;
import Orion.Api.Storage.Repository.Habbo.IHabboRepository;
import Orion.Api.Storage.Repository.Habbo.IHabboRoomsRepository;
import Orion.Storage.Connection;
import Orion.Storage.ConnectionContext;
import Orion.Storage.Connector.HikariConnector;
import Orion.Storage.Provider.ConnectionProvider;
import Orion.Storage.Repository.Emulator.EmulatorRepository;
import Orion.Storage.Repository.Habbo.HabboCurrenciesRepository;
import Orion.Storage.Repository.Habbo.HabboNavigatorRepository;
import Orion.Storage.Repository.Habbo.HabboRepository;
import Orion.Storage.Repository.Habbo.HabboRoomsRepository;
import com.google.inject.AbstractModule;

public class ConnectionModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IConnector.class).to(HikariConnector.class);
        bind(IConnectionProvider.class).to(ConnectionProvider.class);
        bind(IConnectionContext.class).to(ConnectionContext.class);
        bind(IConnection.class).to(Connection.class);

        this.bindRepositories();
    }

    protected void bindRepositories() {
        bind(IEmulatorRepository.class).to(EmulatorRepository.class);

        bind(IHabboRepository.class).to(HabboRepository.class);
        bind(IHabboNavigatorRepository.class).to(HabboNavigatorRepository.class);
        bind(IHabboRoomsRepository.class).to(HabboRoomsRepository.class);
        bind(IHabboCurrenciesRepository.class).to(HabboCurrenciesRepository.class);
    }
}
