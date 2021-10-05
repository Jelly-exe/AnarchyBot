package dev.elliotfrost.anarchybot.command.commands;

import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import net.dv8tion.jda.api.entities.Message;

import java.util.List;

public class Clean implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        try {
            List<Message> messages = ctx.getChannel()
                    .getHistory()
                    .retrievePast(Integer.parseInt(args.get(0)))
                    .complete();
            ctx.getChannel()
                    .deleteMessages(messages)
                    .complete();
        } catch (Exception e) {
            ctx.getChannel()
                    .sendMessage("Error while running command:\n`" + e.getMessage() + "`\n|| The most common error is specifying a value less than 2 or greater than 100. ||")
                    .queue(message ->
                            message.addReaction("U+1F62D")
                                    .queue());
        }
    }

    @Override
    public String getName() {
        return "clean";
    }

    @Override
    public List<String> getAliases() {
        return List.of("clear");
    }
}