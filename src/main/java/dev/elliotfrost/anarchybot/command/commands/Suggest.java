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
        String suggestion = String.join(" ", ctx.getArgs());
        if (suggestion.equals("")) {
            ctx.getChannel().sendMessage("You cannot send a blank suggestion!").queue();
        } else {
            System.out.println("Suggestion Made:" + suggestion);
            TextChannel channel = jda.getTextChannelById(Config.get("SUGGESTIONS_CHANNEL"));

            MessageEmbed embed = new EmbedBuilder()
                    .setTitle("Suggestion from " + ctx.getAuthor().getName(), null)
                    .setDescription(suggestion)
                    .setFooter("Anarchy Bot v2.0 | anarchy.ciputin.cf", "https://i.imgur.com/i4ht6nZ.png")
                    .build();

            assert channel != null;
            channel.sendMessage("<@" + ctx.getAuthor().getId() + "> <@&885161612271575060>").setEmbeds(embed).queue(message -> {
                        message.addReaction("\u2705").queue();
                        message.addReaction("\u274C").queue();
                    }
            );
            channel.deleteMessageById(ctx.getMessage().getId());
        }
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
