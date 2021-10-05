package dev.elliotfrost.anarchybot;

import dev.elliotfrost.anarchybot.database.DatabaseManager;
import dev.elliotfrost.anarchybot.listeners.*;
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
        JDA jda = JDABuilder.createLight(Config.getToken())
                .addEventListeners(new Autorole(), new Listener(), new CommandManager(), new Tickets(), new Suggestions(), new Roles())
                .setActivity(Activity.playing("on anarchy.ciputin.cf"))
                .build();
    }
    public static void main(String[] args) throws LoginException { Bot bot = new Bot(); }
}
