package dev.elliotfrost.anarchybot.listeners;

import dev.elliotfrost.anarchybot.Bot;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class SlashCommands extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommands.class);
    private final CommandManager manager = new CommandManager();

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        switch (event.getName()) {
            case "link":
                boolean successful = true;
                switch (Objects.requireNonNull(event.getSubcommandName())) {
                    case "java":
                        try {
                            Bot.getDatabaseManager().getDatabaseUser().linkJava(event.getUser().getId(), event.getOption("username").getAsString());
                        } catch (Exception e) {
                            System.out.print(e);
                            successful = false;
                        }
                        break;
                    case "bedrock":
                        try {
                            Bot.getDatabaseManager().getDatabaseUser().linkBedrock(event.getUser().getId(), event.getOption("username").getAsString());
                        } catch (Exception e) {
                            System.out.print(e);
                            successful = false;
                        }
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + Objects.requireNonNull(event.getSubcommandName()));
                }
                String reply;
                if (successful) { reply = "Linking was successful!"; } else { reply = "Linking was unsuccessful, please make a ticket!"; }
                event.reply(reply).setEphemeral(true).queue();
                break;
        }
    }
}
