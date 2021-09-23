package dev.elliotfrost.anarchybot.listeners;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Roles extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Roles.class);
    private final CommandManager manager = new CommandManager();

    @Override
    public void onSelectionMenu(SelectionMenuEvent event) {
        if (event.getComponentId().equals("menu:roles")) {
            System.out.println("Testing");

            SelectionMenu menu = SelectionMenu.create("menu:roles")
                    .setPlaceholder("Select a role to assign...")
                    .addOption("Testing 1", "1", "Testing UwU", Emoji.fromMarkdown("\uD83D\uDD12"))
                    .addOption("Testing 2", "2", "Testing UwU", Emoji.fromMarkdown("\uD83D\uDD12"))
                    .build();

            event.getMessage().editMessageComponents(ActionRow.of(menu)).queue(message -> event.reply("Shut the fuck up").setEphemeral(true).queue());

        }
    }
}
