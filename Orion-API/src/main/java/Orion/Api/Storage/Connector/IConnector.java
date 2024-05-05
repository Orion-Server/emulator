package Orion.Api.Storage.Connector;

import Orion.Api.Server.Core.Configuration.IEmulatorEnvironmentSettings;
import com.zaxxer.hikari.HikariDataSource;

public interface IConnector {
    HikariDataSource getDataSource();

    void initialize(IEmulatorEnvironmentSettings config);
}
