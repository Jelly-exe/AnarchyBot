package dev.elliotfrost.anarchybot.listeners;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Role;
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

            SelectionMenu menu = SelectionMenu.create("menu:roles")
                    .setPlaceholder("Select a role to assign...")
                    .addOption("Suggestion Ping", "885161612271575060", "Get pinged when someone makes a suggestion!", Emoji.fromUnicode("U+1F9E0"))
                    .addOption("Tester", "879852881681981531", "Gain access to our testing server and help us debug it!", Emoji.fromMarkdown("\uD83D\uDD12"))
                    .build();

            Role role = event.getGuild().getRoleById(event.getValues().get(0));
            event.getGuild().addRoleToMember(event.getUser().getId(), role).queue();
            event.getMessage().editMessageComponents(ActionRow.of(menu)).queue(message -> event.reply("You now have the role: " + role.getName()).setEphemeral(true).queue());
        }
    }
}
