package dev.elliotfrost.anarchybot.command.commands;

import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.awt.*;
import java.util.List;

public class Help implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        EmbedBuilder helpmessage = new EmbedBuilder()
                .setTitle("List of commands!")
                .setDescription("Look at all my cool features!")
                .setColor(Color.BLUE)
                .addField("Regular commands", "---", false)
                .addField("cmd", "Execute an acceptable command on the Anarchy Server\n*Aliases:\nexec, execute", true)
                .addField("help","Help Message!", true)
                .addField("membercount", "Make the bot count the members in the server!", true)
                .addField("ping", "Test the connection between the bot and the discord\nAlias: ciisgay",true)
                .addField("suggest", "Make a suggestion!", true)
                .addBlankField(false)
                .addField("Slash Commands", "---", false)
                .addField("/link bedrock", "Link your bedrock minecraft account!", true)
                .addField("/link java", "Link your java minecraft account!", true);
        if (ctx.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            helpmessage.addBlankField(false);
            helpmessage.addField("Manage Messages Permission Required", "---", false);
            helpmessage.addField("clean", "Delete a number of message using discord's BulkDelete.\n" +
                    "doesn't work with messages older than 14 days.\nIt also requires that you delete at least 2 and" +
                    " at most 100 messages", true);
            helpmessage.addField("clean2", "Deletes the messages manually (takes longer)", true);
        }

        if (ctx.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            helpmessage.addBlankField(false);
            helpmessage.addField("Administrator Permission required", "---", false);
            helpmessage.addField("rolesmenu", "Call the role selection menu", true);
            helpmessage.addField("ticketmenu", "Call the Ticket menu", true);
        }
        ctx.getChannel().sendMessageEmbeds(helpmessage.build()).queue();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public List<String> getAliases() {
        return List.of();
    }
}