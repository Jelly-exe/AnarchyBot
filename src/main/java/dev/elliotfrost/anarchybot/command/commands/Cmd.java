package dev.elliotfrost.anarchybot.command.commands;

import dev.elliotfrost.anarchybot.Bot;
import dev.elliotfrost.anarchybot.Config;
import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import dev.elliotfrost.anarchybot.functions.CheckArrayContains;
import net.dv8tion.jda.api.Permission;

import java.util.Arrays;
import java.util.List;

public class Cmd implements ICommand {
    private String error;

    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        List<String> nono = Arrays.asList(Config.get("BANNED_COMMANDS").split(","));
        StringBuilder cmd = new StringBuilder();
        ctx.getArgs().forEach(value -> cmd.append(value).append(" "));
        String command = cmd.toString();
        boolean hasbadcommand = new CheckArrayContains().Checker(args, nono);
        if (ctx.getMember().hasPermission(Permission.ADMINISTRATOR)) hasbadcommand = false;
        if (hasbadcommand) {
            ctx.getChannel().sendMessage("You cannot perform command `" + command + "` because you do not have the permission.").queue();
        }
        if (!hasbadcommand) {
            Bot.getP4J().retrieveServerByIdentifier(Config.get("ANARCHY-SERVER-ID"))
                    .flatMap(server -> server.sendCommand(command)
                    .onErrorMap(throwable -> { this.error = throwable.getMessage();
                        return null;
                    }))
                    .execute();
            StringBuilder message = new StringBuilder().append("Command executed!");
            if (this.error != null) message.append("\nError returned: ").append(this.error);
            ctx.getChannel().sendMessage(message).queue();
        }
    }

    @Override
    public String getName() {
        return "cmd";
    }

    @Override
    public List<String> getAliases() {
        return List.of("execute", "exec");
    }
}

