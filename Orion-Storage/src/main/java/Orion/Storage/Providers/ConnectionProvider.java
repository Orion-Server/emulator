package Orion.Storage.Providers;

import Orion.Api.Storage.Providers.IConnectionProvider;
import Orion.Storage.Connectors.HikariConnector;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Singleton
public class ConnectionProvider implements IConnectionProvider {
    @Inject
    private HikariConnector hikariConnector;

    @Override
    public Connection getConnection() throws Exception {
        return this.hikariConnector.getDataSource().getConnection();
    }

    @Override
    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            //this.logger.error("Error while closing connection", e);
        }
    }

    @Override
    public void closeStatement(PreparedStatement statement) {
        try {
            statement.close();
        } catch (Exception e) {
            //this.logger.error("Error while closing statement", e);
        }
    }

    @Override
    public void closeResultSet(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (Exception e) {
            //this.logger.error("Error while closing result set", e);
        }
    }
}
