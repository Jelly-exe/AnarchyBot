package dev.elliotfrost.anarchybot.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.elliotfrost.anarchybot.Config;

public class DatabaseManager {
    private static final HikariConfig config = new HikariConfig();

    private static DatabaseTickets databaseTickets;
    private final HikariDataSource ds;

    public DatabaseManager() {
        config.setJdbcUrl(String.format("jdbc:mysql://%s:3306/%s", Config.get("database-host"), Config.get("database-database")));
        config.setUsername(Config.get("database-user"));
        config.setPassword(Config.get("database-password"));
        config.setMaximumPoolSize(10);

        this.ds = new HikariDataSource(config);

        databaseTickets = new DatabaseTickets(ds);
    }

    public DatabaseTickets getDatabaseTickets() {
        return databaseTickets;
    }
}
