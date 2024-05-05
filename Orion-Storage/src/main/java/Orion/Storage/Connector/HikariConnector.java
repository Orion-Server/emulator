package Orion.Storage.Connector;

import Orion.Api.Server.Core.Configuration.IEmulatorEnvironmentSettings;
import Orion.Api.Storage.Connector.IConnector;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.lang.StringTemplate.STR;

@Singleton
public class HikariConnector implements IConnector {
    private final Logger logger = LogManager.getLogger();

    private HikariDataSource dataSource;

    private IEmulatorEnvironmentSettings config;

    public void initialize(final IEmulatorEnvironmentSettings config) {
        try {
            this.config = config;

            this.initializeConnector();
        } catch (Exception e) {
            this.logger.error(STR."Failed to initialize the database connection: \{e.getMessage()}");
            System.exit(1);
        }
    }

    private void initializeConnector() {
        final String hostname = this.config.getStringOrDefault("db.host", "");
        final String port = this.config.getStringOrDefault("db.port", "");
        final String database = this.config.getStringOrDefault("db.database", "");
        final String params = this.config.getStringOrDefault("db.params", "");
        final String username = this.config.getStringOrDefault("db.username", "");
        final String password = this.config.getStringOrDefault("db.password", "");

        final HikariConfig databaseConfiguration = new HikariConfig();

        databaseConfiguration.setMaximumPoolSize(this.config.getInteger("db.pool.max_size"));
        databaseConfiguration.setMinimumIdle(this.config.getInteger("db.pool.min_size"));

        databaseConfiguration.setJdbcUrl(STR."jdbc:mysql://\{hostname}:\{port}/\{database}\{params}");

        databaseConfiguration.addDataSourceProperty("serverName", hostname);
        databaseConfiguration.addDataSourceProperty("port", port);
        databaseConfiguration.addDataSourceProperty("databaseName", database);
        databaseConfiguration.addDataSourceProperty("user", username);
        databaseConfiguration.addDataSourceProperty("password", password);
        databaseConfiguration.addDataSourceProperty("dataSource.logger", "com.mysql.jdbc.log.StandardLogger");
        databaseConfiguration.addDataSourceProperty("dataSource.logSlowQueries", "true");
        databaseConfiguration.addDataSourceProperty("dataSource.dumpQueriesOnException", "true");
        databaseConfiguration.addDataSourceProperty("prepStmtCacheSize", "500");
        databaseConfiguration.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        databaseConfiguration.addDataSourceProperty("cachePrepStmts", "true");
        databaseConfiguration.addDataSourceProperty("useServerPrepStmts", "true");
        databaseConfiguration.addDataSourceProperty("rewriteBatchedStatements", "true");
        databaseConfiguration.addDataSourceProperty("useUnicode", "true");

        databaseConfiguration.setConnectionTimeout(300000L);
        databaseConfiguration.setLeakDetectionThreshold(20000L);

        try {
            this.dataSource = new HikariDataSource(databaseConfiguration);

            this.logger.info("Database connection established.");
        } catch (Exception e) {
            this.logger.error(STR."Failed to establish database connection: \{e.getMessage()}");
            System.exit(1);
        }
    }

    public HikariDataSource getDataSource() {
        return this.dataSource;
    }
}
