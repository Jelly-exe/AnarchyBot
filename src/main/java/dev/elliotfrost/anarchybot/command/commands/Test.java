package dev.elliotfrost.anarchybot.command.commands;

import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;

public class Test implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();

        SelectionMenu menu = SelectionMenu.create("menu:testing")
                .setPlaceholder("Test")
                .addOption("Test 1", "1")
                .addOption("Test 2", "2")
                .addOption("Test 3", "3")
                .build();



        ctx.getChannel().sendMessage("Testing").setActionRow(menu).queue();
        ctx.getChannel().sendMessage("Testing").setActionRow(Button.primary("test", "test")).queue();
    }

    @Override
    public String getName() {
        return "test";
    }
}