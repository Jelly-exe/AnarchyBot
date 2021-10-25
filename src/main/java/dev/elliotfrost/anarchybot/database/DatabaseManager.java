package dev.elliotfrost.anarchybot.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.elliotfrost.anarchybot.Config;

public class DatabaseManager {
    private static final HikariConfig config = new HikariConfig();

    private static DatabaseTickets databaseTickets;
    private static DatabaseToDo databaseToDo;
    private static DatabaseUser databaseUser;
    private final HikariDataSource ds;

    public DatabaseManager() {
        config.setJdbcUrl(String.format("jdbc:mysql://%s:3306/%s", Config.get("database-host"), Config.get("database-database")));
        config.setUsername(Config.get("database-user"));
        config.setPassword(Config.get("database-password"));
        config.setMaximumPoolSize(10);

        this.ds = new HikariDataSource(config);

        databaseTickets = new DatabaseTickets(ds);
        databaseToDo = new DatabaseToDo(ds);
        databaseUser = new DatabaseUser(ds);
    }

    public DatabaseTickets getDatabaseTickets() {
        return databaseTickets;
    }
    public DatabaseToDo getDatabaseToDo() { return databaseToDo; }
    public DatabaseUser getDatabaseUser() { return databaseUser; }
}
