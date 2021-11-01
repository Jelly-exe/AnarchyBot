package dev.elliotfrost.anarchybot.Scheduled;

import com.coreoz.wisp.Scheduler;
import com.mattmalec.pterodactyl4j.UtilizationState;
import dev.elliotfrost.anarchybot.Bot;
import dev.elliotfrost.anarchybot.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ServerStatus implements Runnable {

    @Override
    public void run() {
        ArrayList<String> messages = new ArrayList<>();
        UtilizationState Powerstate_SMP = Bot.getP4J().retrieveServerByIdentifier(Config.get("SMP-SERVER-ID")).execute().retrieveUtilization().execute().getState();
        UtilizationState Powerstate_ANARCHY = Bot.getP4J().retrieveServerByIdentifier(Config.get("ANARCHY-SERVER-ID")).execute().retrieveUtilization().execute().getState();
        UtilizationState Powerstate_LOBBY = Bot.getP4J().retrieveServerByIdentifier(Config.get("LOBBY-SERVER-ID")).execute().retrieveUtilization().execute().getState();
        UtilizationState Powerstate_BUNGEE = Bot.getP4J().retrieveServerByIdentifier(Config.get("BUNGEE-SERVER-ID")).execute().retrieveUtilization().execute().getState();
        try {
            File myObj = new File("status-messages.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                messages.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        MessageEmbed Anarchy = new EmbedBuilder()
                .setTitle("Status: " + Powerstate_ANARCHY)
                .setDescription("The Anarchy Server!")
                .setFooter("anarchy.ciputin.cf", "https://i.imgur.com/i4ht6nZ.png")
                .setColor(Determinestatecolor(Powerstate_ANARCHY))
                .build();
        MessageEmbed Bungee = new EmbedBuilder()
                .setTitle("Status: " + Powerstate_BUNGEE)
                .setDescription("The Bungee Server!")
                .setFooter("anarchy.ciputin.cf", "https://i.imgur.com/i4ht6nZ.png")
                .setColor(Determinestatecolor(Powerstate_ANARCHY))
                .build();
        MessageEmbed Lobby = new EmbedBuilder()
                .setTitle("Status: " + Powerstate_LOBBY)
                .setDescription("The Lobby Server!")
                .setFooter("anarchy.ciputin.cf", "https://i.imgur.com/i4ht6nZ.png")
                .setColor(Determinestatecolor(Powerstate_ANARCHY))
                .build();
        MessageEmbed SMP = new EmbedBuilder()
                .setTitle("Status: " + Powerstate_SMP)
                .setDescription("The SMP Server!")
                .setFooter("anarchy.ciputin.cf", "https://i.imgur.com/i4ht6nZ.png")
                .setColor(Determinestatecolor(Powerstate_ANARCHY))
                .build();

        Objects.requireNonNull(Objects.requireNonNull(Bot.getJDA()
                                .getGuildById(Config.get("GUILD-ID")))
                        .getTextChannelById(Config.getStatus()))
                .editMessageById(messages.get(0), String.format("Statuses (last updated <t:%s:R>):", Instant.now().getEpochSecond()))
                .setEmbeds(Anarchy, Bungee, Lobby, SMP)
                .complete();
        new Scheduler().gracefullyShutdown();
    }

    private Color Determinestatecolor(UtilizationState Powerstate) {
        if (!String.valueOf(Powerstate).equals("RUNNING")) { return Color.RED; } else { return Color.blue; }
    }
}
