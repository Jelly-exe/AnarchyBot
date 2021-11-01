package dev.elliotfrost.anarchybot.command.commands;

import com.mattmalec.pterodactyl4j.PteroBuilder;
import com.mattmalec.pterodactyl4j.client.entities.PteroClient;
import dev.elliotfrost.anarchybot.Config;
import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import dev.elliotfrost.anarchybot.functions.CheckArrayContains;
import net.dv8tion.jda.api.JDA;

import java.util.Arrays;
import java.util.List;

public class Cmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();
        List<String> args = ctx.getArgs();
        List<String> nono = Arrays.asList(Config.get("BANNED-COMMANDS").split(","));
        String command = String.join(" ", args); // Join the arguments into one string
        boolean hasbadcommand = new CheckArrayContains().Checker(args, nono);
        if (hasbadcommand) {
            ctx.getChannel().sendMessage("You cannot perform command: " + command + "\nReason: insufficient permissions").queue();
        } else {
            PteroClient api = PteroBuilder.createClient("https://panel.skynode.pro", Config.get("PTERO_TOKEN"));
            api.retrieveServerByIdentifier(Config.get("ANARCHY-SERVER-ID"))
                    .flatMap(server -> server.sendCommand(command))
                    .execute();
            ctx.getChannel().sendMessage("Command Executed").queue();
        }
    }

    @Override
    public String getName() {
        return "cmd";
    }

    @Override
    public List<String> getAliases() {
        return List.of();
    }
}

