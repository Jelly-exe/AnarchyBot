package dev.elliotfrost.anarchybot.command.commands;

import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import net.dv8tion.jda.api.requests.restaction.pagination.MessagePaginationAction;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Clean2 implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        MessagePaginationAction messages = ctx.getChannel().getIterableHistory();
        AtomicInteger number = new AtomicInteger();
        messages.forEachAsync(message -> {
            message.delete().queue();
            int value = number.getAndIncrement();
            return value-1 < Integer.parseInt(args.get(0));
        });
    }

    @Override
    public String getName() {
        return "clean2";
    }

    @Override
    public List<String> getAliases() {
        return List.of("clear2");
    }
}
