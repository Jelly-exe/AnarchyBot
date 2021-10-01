package dev.elliotfrost.anarchybot.command.commands;

import com.mattmalec.pterodactyl4j.PteroBuilder;
import com.mattmalec.pterodactyl4j.client.entities.PteroClient;
import com.mattmalec.pterodactyl4j.client.managers.WebSocketManager;
import com.mattmalec.pterodactyl4j.client.managers.WebSocketManager.RequestAction;
import com.mattmalec.pterodactyl4j.exceptions.ServerException;
import dev.elliotfrost.anarchybot.Config;
import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import net.dv8tion.jda.api.JDA;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

public class Cmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();
        List<String> args = ctx.getArgs();
        List<String> nono = Arrays.asList("op", "stop", "ban");
        String command = String.join(" ", args); // Join the arguments into one string
        if (checker(args, nono)) {
            ctx.getChannel().sendMessage("You cannot perform command: " + command).queue();
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
    public boolean checker(List<String> args, List<String> checked) {
        boolean found = false;
        for (String x : args) {
            if (checked.contains(x)) {
                found = true;
                break;
            }
        }
        return found;
    }
}

