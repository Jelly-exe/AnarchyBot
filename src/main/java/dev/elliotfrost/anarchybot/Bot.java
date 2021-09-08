package dev.elliotfrost.anarchybot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot {
    private Bot() throws LoginException {
        JDA jda = JDABuilder.createLight(Config.get("token"))
                .addEventListeners(new Listener())
                .setActivity(Activity.watching("everyone!"))
                .build();
    }
    public static void main(String[] args) throws LoginException {
        Bot bot = new Bot();

    }
}
