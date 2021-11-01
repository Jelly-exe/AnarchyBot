package dev.elliotfrost.anarchybot.Scheduled;

import dev.elliotfrost.anarchybot.Bot;
import dev.elliotfrost.anarchybot.Config;
import net.dv8tion.jda.api.entities.Message;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class NewStatuses {
    public void run() {
        /* Delete old Messages */
        java.util.List<Message> old = Objects.requireNonNull(Objects.requireNonNull(Bot.getJDA().getGuildById(Config.get("GUILD-ID"))).getTextChannelById(Config.getStatus()))
                .getHistory()
                .retrievePast(100)
                .complete();
        /* Send new messages */
        Objects.requireNonNull(Objects.requireNonNull(Bot.getJDA().getGuildById(Config.get("GUILD-ID"))).getTextChannelById(Config.getStatus())).sendMessage("Statuses:").complete();

        /* Get history of said messages */
        List<Message> messages = Objects.requireNonNull(Objects.requireNonNull(Bot.getJDA().getGuildById(Config.get("GUILD-ID"))).getTextChannelById(Config.getStatus())).getIterableHistory().limit(4).complete();

        /* save history */
        try {
            FileWriter myWriter = new FileWriter("status-messages.txt");
            for (Message message : messages) {
                myWriter.append(message.getId()).append("\n");
            }
            myWriter.close();
        } catch (
        IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        for (Message message : old) {
            Objects.requireNonNull(Objects.requireNonNull(Bot.getJDA().getGuildById(Config.get("GUILD-ID"))).getTextChannelById(Config.getStatus())).deleteMessageById(message.getId()).complete();
        }
    }
}
