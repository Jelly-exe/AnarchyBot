package dev.elliotfrost.anarchybot.command.commands;

import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import net.dv8tion.jda.api.JDA;

import java.util.List;
public class Clean implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();
        System.out.println(ctx.getArgs());
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
