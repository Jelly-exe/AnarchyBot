package dev.elliotfrost.anarchybot.command.commands;

import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;

import java.util.List;

public class afk implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        if (ctx.getMember().getNickname().startsWith("[AFK] ")) {
            String[] nick = ctx.getMember().getNickname().split("[AFK] ");
            ctx.getMember().modifyNickname(nick.toString()).complete();
        } else {
            ctx.getMember().modifyNickname("[AFK] " + ctx.getMember().getNickname()).complete();
        }
    }

    @Override
    public String getName() {
        return "afk";
    }

    @Override
    public List<String> getAliases() {
        return List.of();
    }
}
