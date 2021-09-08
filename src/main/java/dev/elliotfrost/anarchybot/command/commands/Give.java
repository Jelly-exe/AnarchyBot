package dev.elliotfrost.anarchybot.command.commands;

import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import io.graversen.minecraft.rcon.MinecraftRcon;
import io.graversen.minecraft.rcon.service.ConnectOptions;
import io.graversen.minecraft.rcon.service.MinecraftRconService;
import io.graversen.minecraft.rcon.service.RconDetails;
import net.dv8tion.jda.api.JDA;

import java.time.Duration;
import java.util.List;

public class Give implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();
        List<String> args =ctx.getArgs();

        final MinecraftRconService minecraftRconService = new MinecraftRconService(
                new RconDetails("51.195.188.184", 25645, "f9cyR8qbkZpPCWD"),
                ConnectOptions.defaults()
        );

        // Let's go!
        minecraftRconService.connectBlocking(Duration.ofSeconds(3));

        // After connecting, we can (crudely) fetch the underlying Minecraft RCON provider
        final MinecraftRcon minecraftRcon = minecraftRconService.minecraftRcon().orElseThrow(IllegalStateException::new);

        // final




    }

    @Override
    public String getName() {
        return "give";
    }

    @Override
    public List<String> getAliases() {
        return List.of();
    }

}
