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
                .addEventListeners(new Autorole(), new Listener(), new CommandManager(), new Tickets(), new Suggestions(), new Roles(), new SlashCommands())
                .setActivity(Activity.playing("on anarchy.ciputin.cf"))
                .build();
        SubcommandData java = new SubcommandData("java", "Link your Java Minecraft Account!")
                .addOption(OptionType.STRING,"username", "Your minecraft username", true);
        SubcommandData bedrock = new SubcommandData("bedrock","Link your Bedrock Minecraft Account!")
                .addOption(OptionType.STRING,"username", "Your minecraft username", true);
        CommandData link = new CommandData("link","Link your minecraft accounts!")
                .addSubcommands(java, bedrock);

        jda.upsertCommand(link).complete();

        this.jda = jda;
    }
    public static void main(String[] args) throws LoginException { Bot bot = new Bot(); }
}
