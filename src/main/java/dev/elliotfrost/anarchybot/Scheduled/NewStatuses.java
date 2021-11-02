package dev.elliotfrost.anarchybot.Scheduled;

import dev.elliotfrost.anarchybot.Bot;
import dev.elliotfrost.anarchybot.Config;
import net.dv8tion.jda.api.entities.Message;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class NewStatuses {
    public void run() {
        /* Delete old Messages */
        java.util.List<Message> old = Objects.requireNonNull(Objects.requireNonNull(Bot.getJDA().getGuildById(Config.get("GUILD-ID"))).getTextChannelById(Config.getStatus()))
                .getHistory()
                .retrievePast(100)
                .complete();
        /* Send new messages */
        String id = Objects.requireNonNull(Objects.requireNonNull(Bot.getJDA().getGuildById(Config.get("GUILD-ID"))).getTextChannelById(Config.getStatus())).sendMessage("Statuses:").complete().getId();
        /* save message id */
        try {
            FileWriter myWriter = new FileWriter("status-messages.txt");
            myWriter.write(id);
            myWriter.close();
        } catch (
        IOException e) {
            System.out.println("An error occurred writing the status-message id.");
            e.printStackTrace();
        }
        for (Message message : old) {
            Objects.requireNonNull(Objects.requireNonNull(Bot.getJDA().getGuildById(Config.get("GUILD-ID"))).getTextChannelById(Config.getStatus())).deleteMessageById(message.getId()).complete();
        }
    }
}
