package games.trident.skills.database;

import com.zaxxer.hikari.HikariDataSource;
import games.trident.skills.conf.ConfSql;

import javax.annotation.Nullable;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseHandler implements Database {
    private static final String DEFAULT_MYSQL_DRIVER = "org.mariadb.jdbc.Driver";
    private DatabaseCredentials dbCreds;
    private final HikariDataSource hikariSource = new HikariDataSource();

    protected int minIdle = 30;
    protected int maxPoolSize = 100;
    protected int connectionTimeoutMs = 120 * 1000;
    protected int idleTimeoutMs = 45 * 1000;
    protected int maxLifetimeMs = 30 * 60 * 1000;
    protected int leakDetectionThresholdMs = 60 * 5000;
    protected String connectionTestQuery = "SELECT 1";
    protected boolean cachePreparedStatements = true;
    protected int preparedStatementCache = 250;
    protected int maxPreparedStatementCache = 2048;

    public void init(DatabaseCredentials dbCreds, String driver) {
        this.dbCreds = dbCreds;
        this.hikariSource.setDriverClassName(driver);

        String connURL = dbCreds.getHost();

        if (dbCreds.getPort() > 0) {
            connURL = dbCreds.getHost() + ":" + dbCreds.getPort();
        }

        // https://stackoverflow.com/questions/3040597/jdbc-character-encoding
        this.hikariSource.setJdbcUrl("jdbc:mariadb://" + connURL + "/" + dbCreds.getName() + "?characterEncoding=UTF-8&autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowMultiQueries=true");
        this.hikariSource.setUsername(dbCreds.getUser());
        this.hikariSource.setPassword(dbCreds.getPass());
        this.hikariSource.setMinimumIdle(minIdle);
        this.hikariSource.setMaximumPoolSize(maxPoolSize);
        this.hikariSource.setConnectionTimeout(connectionTimeoutMs);
        this.hikariSource.setIdleTimeout(idleTimeoutMs);
        this.hikariSource.setMaxLifetime(maxLifetimeMs);
        this.hikariSource.setLeakDetectionThreshold(leakDetectionThresholdMs);
        this.hikariSource.setConnectionTestQuery(connectionTestQuery);
        this.hikariSource.addDataSourceProperty("cachePrepStmts", "" + cachePreparedStatements);
        this.hikariSource.addDataSourceProperty("prepStmtCacheSize", "" + preparedStatementCache);
        this.hikariSource.addDataSourceProperty("prepStmtCacheSqlLimit", "" + maxPreparedStatementCache);

        try {
            this.hikariSource.setLogWriter(new PrintWriter(System.out));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void init(DatabaseCredentials credentials) {
        this.init(credentials, DEFAULT_MYSQL_DRIVER);
    }

    public void init(ConfSql configSQL) {
        DatabaseCredentials credentials = new DatabaseCredentials(
                configSQL.getHost(),
                configSQL.getPort(),
                configSQL.getDatabaseName(),
                configSQL.getUser(),
                configSQL.getPassword()
        );

        this.minIdle = configSQL.getMinIdle() < 0 ? 1 : configSQL.getMinIdle();
        this.maxPoolSize =  Math.max(configSQL.getMaxConnections(), 1);
        init(credentials);
    }

    public void close() {
        if (!this.hikariSource.isClosed()) {
            this.hikariSource.close();
        }
    }

    @Override
    public DatabaseCredentials getCredentials() {
        return this.dbCreds;
    }

    @Nullable
    @Override
    public Connection getConnection() {
        try {
            return this.hikariSource.getConnection();
        } catch (SQLException e) {
            System.out.println("[DatabaseHandler] Unable to grab a connection from the connection pool!");
            e.printStackTrace();
        }

        return null;
    }
}