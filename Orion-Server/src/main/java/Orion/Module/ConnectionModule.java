package Orion.Module;

import Orion.Api.Storage.Connectors.IConnector;
import Orion.Api.Storage.IConnection;
import Orion.Api.Storage.IConnectionContext;
import Orion.Api.Storage.Providers.IConnectionProvider;
import Orion.Storage.Connection;
import Orion.Storage.ConnectionContext;
import Orion.Storage.Connectors.HikariConnector;
import Orion.Storage.Providers.ConnectionProvider;
import com.google.inject.AbstractModule;

public class ConnectionModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IConnector.class).to(HikariConnector.class);
        bind(IConnectionProvider.class).to(ConnectionProvider.class);
        bind(IConnectionContext.class).to(ConnectionContext.class);
        bind(IConnection.class).to(Connection.class);
    }
}
