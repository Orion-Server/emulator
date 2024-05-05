package Orion.Storage.Provider;

import Orion.Api.Storage.Provider.IConnectionProvider;
import Orion.Storage.Connector.HikariConnector;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Singleton
public class ConnectionProvider implements IConnectionProvider {
    private final Logger logger = LogManager.getLogger();

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
            this.logger.error(STR."Error while closing connection: \{e.getMessage()}");
        }
    }

    @Override
    public void closeStatement(PreparedStatement statement) {
        try {
            statement.close();
        } catch (Exception e) {
            this.logger.error(STR."Error while closing statement: \{e.getMessage()}");
        }
    }

    @Override
    public void closeResultSet(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (Exception e) {
            this.logger.error(STR."Error while closing result set: \{e.getMessage()}");
        }
    }
}
