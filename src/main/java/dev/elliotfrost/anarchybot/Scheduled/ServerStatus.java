package dev.elliotfrost.anarchybot.Scheduled;

import com.mattmalec.pterodactyl4j.PteroBuilder;
import com.mattmalec.pterodactyl4j.UtilizationState;
import com.mattmalec.pterodactyl4j.client.entities.PteroClient;
import dev.elliotfrost.anarchybot.Bot;
import dev.elliotfrost.anarchybot.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ServerStatus implements Runnable {

    @Override
    public void run() {
        if (Config.get("DEV").equals("true")) {return;}
        ArrayList<String> messages = new ArrayList<>();
        PteroClient api = PteroBuilder.createClient("https://panel.skynode.pro", Config.get("PTERO_TOKEN"));
        UtilizationState Powerstate_SMP = api.retrieveServerByIdentifier(Config.get("SMP-SERVER-ID")).execute().retrieveUtilization().execute().getState();
        UtilizationState Powerstate_ANARCHY = api.retrieveServerByIdentifier(Config.get("ANARCHY-SERVER-ID")).execute().retrieveUtilization().execute().getState();
        UtilizationState Powerstate_LOBBY = api.retrieveServerByIdentifier(Config.get("LOBBY-SERVER-ID")).execute().retrieveUtilization().execute().getState();
        UtilizationState Powerstate_BUNGEE = api.retrieveServerByIdentifier(Config.get("BUNGEE-SERVER-ID")).execute().retrieveUtilization().execute().getState();
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
                .setDescription("The ANARCHY Server!")
                .setFooter("anarchy.ciputin.cf","https://i.imgur.com/i4ht6nZ.png")
                .setColor(Determinestatecolor(Powerstate_ANARCHY))
                .build();
        MessageEmbed Bungee = new EmbedBuilder()
                .setTitle("Status: " + Powerstate_BUNGEE)
                .setDescription("The Bungee Server!")
                .setFooter("anarchy.ciputin.cf","https://i.imgur.com/i4ht6nZ.png")
                .setColor(Determinestatecolor(Powerstate_ANARCHY))
                .build();
        MessageEmbed Lobby = new EmbedBuilder()
                .setTitle("Status: " + Powerstate_LOBBY)
                .setDescription("The Lobby Server!")
                .setFooter("anarchy.ciputin.cf","https://i.imgur.com/i4ht6nZ.png")
                .setColor(Determinestatecolor(Powerstate_ANARCHY))
                .build();
        MessageEmbed SMP = new EmbedBuilder()
                .setTitle("Status: " + Powerstate_SMP)
                .setDescription("The SMP Server!")
                .setFooter("anarchy.ciputin.cf","https://i.imgur.com/i4ht6nZ.png")
                .setColor(Determinestatecolor(Powerstate_ANARCHY))
                .build();

        Objects.requireNonNull(Objects.requireNonNull(Bot.getJDA()
                                .getGuildById(Config.get("GUILD-ID")))
                        .getTextChannelById(Config.get("STATUS-CHANNEL")))
                .editMessageById(messages.get(0), String.format("Statuses (last updated <t:%s:R>):", Instant.now().getEpochSecond()))
                .setEmbeds(Anarchy, Bungee, Lobby, SMP)
                .complete();
    }
    public void newStatuses() {
        if (Config.get("DEV").equals("true")) {return;}
        /* Delete old Messages */
            List<Message> old = Objects.requireNonNull(Objects.requireNonNull(Bot.getJDA().getGuildById(Config.get("GUILD-ID"))).getTextChannelById(Config.get("STATUS-CHANNEL")))
                    .getHistory()
                    .retrievePast(100)
                    .complete();
        /* Send new messages */
        Objects.requireNonNull(Objects.requireNonNull(Bot.getJDA().getGuildById(Config.get("GUILD-ID"))).getTextChannelById(Config.get("STATUS-CHANNEL"))).sendMessage("Statuses:").complete();

        /* Get history of said messages */
        List<Message> messages = Objects.requireNonNull(Objects.requireNonNull(Bot.getJDA().getGuildById(Config.get("GUILD-ID"))).getTextChannelById(Config.get("STATUS-CHANNEL"))).getIterableHistory().limit(4).complete();

        /* save history */
        try {
            FileWriter myWriter = new FileWriter("status-messages.txt");
            for (Message message : messages) {
                myWriter.append(message.getId()).append("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        for (Message message : old) {
            Objects.requireNonNull(Objects.requireNonNull(Bot.getJDA().getGuildById(Config.get("GUILD-ID"))).getTextChannelById(Config.get("STATUS-CHANNEL"))).deleteMessageById(message.getId()).complete();
        }
    }

    private Color Determinestatecolor(UtilizationState Powerstate) {
        if (!String.valueOf(Powerstate).equals("RUNNING")) { return Color.RED; } else { return Color.blue; }
    }
}
