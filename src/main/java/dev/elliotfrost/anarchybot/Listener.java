package dev.elliotfrost.anarchybot;

import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.requests.restaction.pagination.ReactionPaginationAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Listener extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);
    private final CommandManager manager = new CommandManager();

    @Override
    public void onReady(ReadyEvent event) {
        LOGGER.info("{} is ready", event.getJDA().getSelfUser().getAsTag());
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        User user = event.getAuthor();

        if (user.isBot() || event.isWebhookMessage()) { return; }

        String prefix = Config.get("prefix");
        String raw = event.getMessage().getContentRaw();

        if (raw.equalsIgnoreCase(prefix + "shutdown")
                && user.getId().equals(Config.get("owner_id"))) {
            LOGGER.info("Shutting down");
            event.getJDA().shutdown();
            BotCommons.shutdown(event.getJDA());

            return;
        }

        if (raw.startsWith(prefix)) {
            manager.handle(event);
        }
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (event.getChannel().getId().equals(Config.get("SUGGESTIONS_CHANNEL"))) {
            try {
                String reaction;

                if (event.getReactionEmote().getEmoji().equals("\u2705")) {
                    reaction = "\u274C";
                } else if (event.getReactionEmote().getEmoji().equals("\u274C")) {
                    reaction = "\u2705";
                } else {
                    event.getReaction().removeReaction(event.getUser()).queue();
                    return;
                }
                ReactionPaginationAction reactions = event.getChannel().retrieveReactionUsersById(event.getMessageId(), reaction);

                reactions.forEachAsync((user) -> {
                    if (user.equals(event.getUser()) && !user.isBot()) {
                        event.getReaction().removeReaction(user).queue();
                        return true;
                    }
                    ;
                    return false;
                });
            } catch (Exception e) {
                event.getReaction().removeReaction(event.getUser()).queue();
            }
        }

    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("test1")) {
            event.reply("Testing")
                    .addActionRow(Button.primary("test", "Test"))
                    .queue();
        }
    }

//    @Override
//    public void onButtonClick(ButtonClickEvent event) {
//        if (event.getComponentId().equals("button:test")) {
//            event.reply("Test").setEphemeral(true).queue();
//        }
//    }

    @Override
    public void onSelectionMenu(SelectionMenuEvent event) {
        if (event.getComponentId().equals("menu:tickets")) {
            List<String> values = event.getValues();

            event.reply("Creating the ticket goes here. Selected Value: `" + values.get(0) + "`")
                    .setEphemeral(true)
                    .queue();
        }
    }
}
