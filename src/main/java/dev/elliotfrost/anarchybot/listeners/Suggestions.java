package dev.elliotfrost.anarchybot.listeners;

import dev.elliotfrost.anarchybot.Config;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.pagination.ReactionPaginationAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Suggestions extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Suggestions.class);
    private final CommandManager manager = new CommandManager();

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
                    event.getReaction().removeReaction(Objects.requireNonNull(event.getUser())).queue();
                    return;
                }
                ReactionPaginationAction reactions = event.getChannel().retrieveReactionUsersById(event.getMessageId(), reaction);

                reactions.forEachAsync((user) -> {
                    if (user.equals(event.getUser()) && !user.isBot()) {
                        event.getReaction().removeReaction(user).queue();
                        return true;
                    }

                    return false;
                });
            } catch (Exception e) {
                event.getReaction().removeReaction(Objects.requireNonNull(event.getUser())).queue();
            }
        }
    }
}
