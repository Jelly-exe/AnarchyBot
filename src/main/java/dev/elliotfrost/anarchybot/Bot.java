package dev.elliotfrost.anarchybot;

import dev.elliotfrost.anarchybot.database.DatabaseManager;
import dev.elliotfrost.anarchybot.listeners.CommandManager;
import dev.elliotfrost.anarchybot.listeners.Listener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot {
    private static DatabaseManager databaseManager = new DatabaseManager();

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    private Bot() throws LoginException {
        JDA jda = JDABuilder.createLight(Config.get("token"))
                .addEventListeners(new Listener(), new CommandManager())
                .setActivity(Activity.watching("everyone!"))
                .build();
    }
    public static void main(String[] args) throws LoginException {
        Bot bot = new Bot();
    }
}
