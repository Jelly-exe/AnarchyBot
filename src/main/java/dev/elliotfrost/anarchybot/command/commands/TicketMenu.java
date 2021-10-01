package dev.elliotfrost.anarchybot.command.commands;

import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;

import java.util.List;

public class TicketMenu implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();

        if (!ctx.getMember().hasPermission(Permission.valueOf("ADMINISTRATOR"))) return;

        SelectionMenu menu = SelectionMenu.create("menu:tickets")
                .setPlaceholder("Select a reason...")
                .addOption("Server Bug / Issue", "bug", "Report any bugs or issues with the minecraft server.", Emoji.fromMarkdown("\uD83D\uDC1B"))
                .addOption("Player Report", "report", "Report a players actions (discord or server).", Emoji.fromMarkdown("\uD83D\uDD12"))
                .addOption("Support", "support", "Get help with anything server related.", Emoji.fromMarkdown("\u2753"))
                .addOption("Other", "other", "Anything which doesnt fall into an above category.", Emoji.fromMarkdown("\uD83D\uDCF0"))
                .build();

        MessageEmbed embed = new EmbedBuilder()
                .setTitle("Support Ticket", null)
                .setDescription("To open a ticket, please select one of the options from the dropdown below.\n")
                .setFooter("Anarchy Bot v2.0 | anarchy.ciputin.cf", "https://i.imgur.com/i4ht6nZ.png")
                .build();



        ctx.getChannel().sendMessageEmbeds(embed).setActionRow(menu).queue();
        ctx.getChannel().deleteMessageById(ctx.getMessage().getId()).queue();
    }

    @Override
    public String getName() {
        return "ticketmenu";
    }

    @Override
    public List<String> getAliases() {
        return List.of();
    }
}