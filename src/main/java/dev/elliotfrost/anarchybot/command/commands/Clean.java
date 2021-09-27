package dev.elliotfrost.anarchybot.command.commands;

import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.requests.restaction.pagination.MessagePaginationAction;

import java.util.Collection;
import java.util.List;

public class Clean implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        List<Message> messages = ctx.getChannel().getHistory().retrievePast(Integer.parseInt(args.get(0))).complete();
        ctx.getChannel().deleteMessages(messages).complete();
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