package dev.elliotfrost.anarchybot.command.commands;

import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;

import java.util.List;

public class afk implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        String name = ctx.getMember().getNickname();
        if (name == null) name =  ctx.getAuthor().getName();
        if (name.startsWith("[AFK] ")) {
            String withoutafk = name.substring(5);
            ctx.getMember().modifyNickname(withoutafk).complete();
        } else {
            ctx.getMember().modifyNickname("[AFK] " + name).complete();
        }
    }

    @Override
    public String getName() {
        return "afk";
    }

    @Override
    public List<String> getAliases() {
        return List.of("offline", "away");
    }
}
