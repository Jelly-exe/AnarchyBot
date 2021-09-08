package dev.elliotfrost.anarchybot.command.commands;

import dev.elliotfrost.anarchybot.Config;
import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class Suggest implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();
        System.out.println("Test");
        TextChannel channel = jda.getTextChannelById(Config.get("SUGGESTIONS_CHANNEL"));
        String suggestion = String.join(" ", ctx.getArgs());

        MessageEmbed embed = new EmbedBuilder()
                .setTitle("Suggestion from " + ctx.getAuthor().getName(), null)
                .setDescription(suggestion)
                .setFooter("Anarchy Bot v2.0 | skynodeanarchy.ciputin.cf", "https://i.imgur.com/i4ht6nZ.png")
                .build();

        assert channel != null;
        channel.sendMessageEmbeds(embed).queue(message -> {
            message.addReaction("\u2705").queue();
            message.addReaction("\u274C").queue();
        });

    }

    @Override
    public String getName() {
        return "suggest";
    }

    @Override
    public List<String> getAliases() {
        return List.of();
    }
}
