package dev.elliotfrost.anarchybot.command.commands;

import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import net.dv8tion.jda.api.JDA;

import java.util.List;
public class Clean implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();
        List<String> args = ctx.getArgs();

        for (int i = 0; i <= Integer.parseInt(args.get(0)); i = i + 1) {
            String a = ctx.getChannel().getLatestMessageId();
            ctx.getChannel().deleteMessageById(a);
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
