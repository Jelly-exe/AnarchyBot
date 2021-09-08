package dev.elliotfrost.anarchybot.command.commands;

import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import net.dv8tion.jda.api.JDA;

import java.util.List;

public class Give2 implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();
        List<String> args = ctx.getArgs();

        String ign = args.get(0), item = args.get(1);
        int amount = Integer.parseInt(args.get(2));

        String command = String.format("give %s minecraft:%s %s", ign, item, amount);

        // GiveCommand giveCommand = new GiveCommand(new Target(ign), new MinecraftItem(item), "{}", amount);
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

