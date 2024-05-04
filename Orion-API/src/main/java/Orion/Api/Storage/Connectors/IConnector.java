package Orion.Api.Storage.Connectors;

import com.zaxxer.hikari.HikariDataSource;

public interface IConnector {
    HikariDataSource getDataSource();
}
