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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerStatus implements Runnable {

    public void BungeeStatus() {
        ArrayList<String> messages = new ArrayList<>();
        PteroClient api = PteroBuilder.createClient("https://panel.skynode.pro", Config.get("PTERO_TOKEN"));
        UtilizationState Powerstate = api.retrieveServerByIdentifier(Config.get("BUNGEE-SERVER-ID")).execute().retrieveUtilization().execute().getState();
        // Objects.requireNonNull(Bot.getJDA().getGuildById(Config.get("GUILD-ID")).getTextChannelById(Config.get("STATUS-CHANNEL"))).editMessageById().queue();
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
        MessageEmbed embed = new EmbedBuilder()
                .setTitle("Status: " + Powerstate.toString())
                .setDescription("The server that you actually connect to!")
                .setFooter("anarchy.ciputin.cf","https://i.imgur.com/i4ht6nZ.png")
                .setColor(Determinestatecolor(Powerstate))
                .build();
        Bot.getJDA().getGuildById(Config.get("GUILD-ID")).getTextChannelById(Config.get("STATUS-CHANNEL")).editMessageEmbedsById(messages.get(3), embed).queue();
    }

    @Override
    public void run() {
        if (Config.get("DEV").equals("true")) {return;}
        BungeeStatus();
        AnarchyStatus();
        LobbyStatus();
        SMPStatus();
    }
    public void newStatuses() {
        if (Config.get("DEV").equals("true")) {return;}
        /* Delete old Messages */
            List<Message> old = Bot.getJDA().getGuildById(Config.get("GUILD-ID")).getTextChannelById(Config.get("STATUS-CHANNEL"))
                    .getHistory()
                    .retrievePast(100)
                    .complete();
        /* Send new messages */
        Bot.getJDA().getGuildById(Config.get("GUILD-ID")).getTextChannelById(Config.get("STATUS-CHANNEL")).sendMessage("BungeeCord:").complete();
        Bot.getJDA().getGuildById(Config.get("GUILD-ID")).getTextChannelById(Config.get("STATUS-CHANNEL")).sendMessage("Anarchy:").complete();
        Bot.getJDA().getGuildById(Config.get("GUILD-ID")).getTextChannelById(Config.get("STATUS-CHANNEL")).sendMessage("Lobby:").complete();
        Bot.getJDA().getGuildById(Config.get("GUILD-ID")).getTextChannelById(Config.get("STATUS-CHANNEL")).sendMessage("SMP:").complete();

        /* Get history of said messages */
        List<Message> messages = Bot.getJDA().getGuildById(Config.get("GUILD-ID")).getTextChannelById(Config.get("STATUS-CHANNEL")).getIterableHistory().limit(4).complete();

        /* save history */
        try {
            FileWriter myWriter = new FileWriter("status-messages.txt");
            for (int i = 0, messagesSize = messages.size(); i < messagesSize; i++) {
                Message message = messages.get(i);
                myWriter.append(message.getId() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        for (Message message : old) {
            Bot.getJDA().getGuildById(Config.get("GUILD-ID")).getTextChannelById(Config.get("STATUS-CHANNEL")).deleteMessageById(message.getId()).complete();
        }
    }

    public void LobbyStatus() {
        ArrayList<String> messages = new ArrayList<>();
        PteroClient api = PteroBuilder.createClient("https://panel.skynode.pro", Config.get("PTERO_TOKEN"));
        UtilizationState Powerstate = api.retrieveServerByIdentifier(Config.get("LOBBY-SERVER-ID")).execute().retrieveUtilization().execute().getState();
        // Objects.requireNonNull(Bot.getJDA().getGuildById(Config.get("GUILD-ID")).getTextChannelById(Config.get("STATUS-CHANNEL"))).editMessageById().queue();
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
        MessageEmbed embed = new EmbedBuilder()
                .setTitle("Status: " + Powerstate.toString())
                .setDescription("The server that you connect to on join!")
                .setFooter("anarchy.ciputin.cf", "https://i.imgur.com/i4ht6nZ.png")
                .setColor(Determinestatecolor(Powerstate))
                .build();
        Bot.getJDA().getGuildById(Config.get("GUILD-ID")).getTextChannelById(Config.get("STATUS-CHANNEL")).editMessageEmbedsById(messages.get(1), embed).queue();
    }

    public void AnarchyStatus() {
        ArrayList<String> messages = new ArrayList<>();
        PteroClient api = PteroBuilder.createClient("https://panel.skynode.pro", Config.get("PTERO_TOKEN"));
        UtilizationState Powerstate = api.retrieveServerByIdentifier(Config.get("ANARCHY-SERVER-ID")).execute().retrieveUtilization().execute().getState();
        // Objects.requireNonNull(Bot.getJDA().getGuildById(Config.get("GUILD-ID")).getTextChannelById(Config.get("STATUS-CHANNEL"))).editMessageById().queue();
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
        MessageEmbed embed = new EmbedBuilder()
                .setTitle("Status: " + Powerstate.toString())
                .setDescription("The main event :smiling_imp:")
                .setFooter("anarchy.ciputin.cf","https://i.imgur.com/i4ht6nZ.png")
                .setColor(Determinestatecolor(Powerstate))
                .build();
        Bot.getJDA().getGuildById(Config.get("GUILD-ID")).getTextChannelById(Config.get("STATUS-CHANNEL")).editMessageEmbedsById(messages.get(2), embed).queue();
    }

    public void SMPStatus() {
        ArrayList<String> messages = new ArrayList<>();
        PteroClient api = PteroBuilder.createClient("https://panel.skynode.pro", Config.get("PTERO_TOKEN"));
        UtilizationState Powerstate = api.retrieveServerByIdentifier(Config.get("SMP-SERVER-ID")).execute().retrieveUtilization().execute().getState();
        // Objects.requireNonNull(Bot.getJDA().getGuildById(Config.get("GUILD-ID")).getTextChannelById(Config.get("STATUS-CHANNEL"))).editMessageById().queue();
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
        MessageEmbed embed = new EmbedBuilder()
                .setTitle("Status: " + Powerstate.toString())
                .setDescription("The SMP Server!")
                .setFooter("anarchy.ciputin.cf","https://i.imgur.com/i4ht6nZ.png")
                .setColor(Determinestatecolor(Powerstate))
                .build();
        Bot.getJDA().getGuildById(Config.get("GUILD-ID")).getTextChannelById(Config.get("STATUS-CHANNEL")).editMessageEmbedsById(messages.get(0), embed).queue();
    }
    private Color Determinestatecolor(UtilizationState Powerstate) {
        if (String.valueOf(Powerstate) != "RUNNING") { return Color.RED; } else { return Color.blue; }
    }
}
