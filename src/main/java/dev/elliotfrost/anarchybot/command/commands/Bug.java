package dev.elliotfrost.anarchybot.command.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

import java.awt.*;

public class Bug { // Thought I was gonna have seperate endpoints for each, so the filename is misleading
    /*
    public void onButtonClick(ButtonClickEvent event) { // Idk if this is required bc of the command handler, but it's here
        String[] id = event.getComponentId().split(":"); // this is the custom id we specified in our button
        String authorId = id[0];
        String type = id[1];

        if (!authorId.equals(event.getUser().getId()))
            return;
        event.deferEdit().queue();

        MessageChannel channel = event.getChannel();

        String username = event.getUser().getName() + "#" + event.getUser().getDiscriminator();
        // Integer ticketnum =

        switch (type)
        {
            case "bug":
                event.getGuild()
                        .createTextChannel("%s's | 1 ticket(s)", username)
                        .setParent("867535393083490324")
                        .addMemberPermissionOverride(authorId, 1024)
                        .addRolePermissionOverride(866757654244622366, ,3072)
                        .queue;
                Message pings = event.getChannel()
                        .sendMessage("<@%s> | <@&867133159137214514>", authorId);
                MessageEmbed embed = new EmbedBuilder()
                        .setTitle("%s#%s has a bug / issue to report!", event.getUser().getName(), event.getUser().getDiscriminator())
                        .setColor(Color.RED)
                        .addField("User has had", "%s Tickets", true)

            case "report":
            case "support":
            case "other":
        }
    }
     */
}
