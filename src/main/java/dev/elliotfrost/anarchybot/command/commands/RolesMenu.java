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

public class RolesMenu implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();

        if (!ctx.getMember().hasPermission(Permission.ADMINISTRATOR)) { return; }

        SelectionMenu menu = SelectionMenu.create("menu:roles")
                .setPlaceholder("Select a role to assign...")
                .addOption("Suggestion Ping", "885161612271575060", "Get pinged when someone makes a suggestion!", Emoji.fromUnicode("U+1F9E0"))
                .addOption("Tester", "879852881681981531", "Gain access to our testing server and help us debug it!", Emoji.fromMarkdown("\uD83D\uDD12"))
                .build();

        MessageEmbed embed = new EmbedBuilder()
                .setTitle("Role Selection", null)
                .setDescription("To get roles, select one of the options below.")
                .setFooter("Anarchy Bot v2.0 | anarchy.ciputin.cf", "https://i.imgur.com/i4ht6nZ.png")
                .build();

        ctx.getChannel().sendMessageEmbeds(embed).setActionRow(menu).queue();
    }

    @Override
    public String getName() {
        return "rolesmenu";
    }

    @Override
    public List<String> getAliases() {
        return List.of();
    }
}