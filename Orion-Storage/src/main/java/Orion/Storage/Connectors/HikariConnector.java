package Orion.Storage.Connectors;

import Orion.Api.Server.Core.Configuration.IEmulatorEnvironmentSettings;
import Orion.Api.Storage.Connectors.IConnector;
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

    @Inject
    public HikariConnector(IEmulatorEnvironmentSettings config) {
        try {
            this.config = config;
            this.initializeConnector();
        } catch (Exception e) {
            // log error
        }
    }

    private void initializeConnector() {
        HikariConfig databaseConfiguration = new HikariConfig();

        databaseConfiguration.setMaximumPoolSize(this.config.getInteger("db.pool.maxsize"));
        databaseConfiguration.setMinimumIdle(this.config.getInteger("db.pool.minsize"));
        databaseConfiguration.setJdbcUrl(STR."jdbc:mysql://\{this.config.getString("db.hostname")}:\{this.config.getString("db.port")}/\{this.config.getString("db.database")}\{this.config.getString("db.params")}");
        databaseConfiguration.addDataSourceProperty("serverName", this.config.getString("db.hostname"));
        databaseConfiguration.addDataSourceProperty("port", this.config.getString("db.port"));
        databaseConfiguration.addDataSourceProperty("databaseName", this.config.getString("db.database"));
        databaseConfiguration.addDataSourceProperty("user", this.config.getString("db.username"));
        databaseConfiguration.addDataSourceProperty("password", this.config.getString("db.password"));
        databaseConfiguration.addDataSourceProperty("dataSource.logger", "com.mysql.jdbc.log.StandardLogger");
        databaseConfiguration.addDataSourceProperty("dataSource.logSlowQueries", "true");
        databaseConfiguration.addDataSourceProperty("dataSource.dumpQueriesOnException", "true");
        databaseConfiguration.addDataSourceProperty("prepStmtCacheSize", "500");
        databaseConfiguration.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        databaseConfiguration.addDataSourceProperty("cachePrepStmts", "true");
        databaseConfiguration.addDataSourceProperty("useServerPrepStmts", "true");
        databaseConfiguration.addDataSourceProperty("rewriteBatchedStatements", "true");
        databaseConfiguration.addDataSourceProperty("useUnicode", "true");
        databaseConfiguration.setAutoCommit(true);
        databaseConfiguration.setConnectionTimeout(300000L);
        databaseConfiguration.setValidationTimeout(5000L);
        databaseConfiguration.setLeakDetectionThreshold(20000L);
        databaseConfiguration.setMaxLifetime(1800000L);
        databaseConfiguration.setIdleTimeout(600000L);

        try {
            this.dataSource = new HikariDataSource(databaseConfiguration);

            logger.info("Database connection established.");
        } catch (Exception e) {
            logger.error(STR."Failed to establish database connection: \{e.getMessage()}");
        }
    }

    public HikariDataSource getDataSource() {
        return this.dataSource;
    }
}
