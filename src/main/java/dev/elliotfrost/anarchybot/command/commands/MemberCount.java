package dev.elliotfrost.anarchybot.command.commands;

import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import net.dv8tion.jda.api.JDA;

import java.util.List;

public class MemberCount implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();

        ctx.getChannel().sendMessage("There is " + ctx.getGuild().getMemberCount() + " members in the Anarchy Server.").queue();
    }

    @Override
    public String getName() {
        return "membercount";
    }

    @Override
    public List<String> getAliases() {
        return List.of();
    }

}