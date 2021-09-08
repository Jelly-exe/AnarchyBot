package dev.elliotfrost.anarchybot.command.commands;

import com.mattmalec.pterodactyl4j.PteroBuilder;
import com.mattmalec.pterodactyl4j.client.entities.PteroClient;
import dev.elliotfrost.anarchybot.Config;
import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import net.dv8tion.jda.api.JDA;

import java.util.List;

public class Give2 implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();
        List<String> args = ctx.getArgs();
        
        Map<String, String> env = System.getenv();

        String ign = args.get(0), item = args.get(1);
        int amount = Integer.parseInt(args.get(2));

        String command = String.format("give %s minecraft:%s %s", ign, item, amount);

        PteroClient api = PteroBuilder.createClient("https://panel.skynode.pro", Config.get(env.token)); //This needs to be an ENV Variable stat
        api.retrieveServerByIdentifier("1ab25fdd").flatMap(server -> server.sendCommand(command)).executeAsync();

        ctx.getChannel().sendMessage("Done shit").queue();
    }

    @Override
    public String getName() {
        return "give2";
    }

    @Override
    public List<String> getAliases() {
        return List.of();
    }

}

