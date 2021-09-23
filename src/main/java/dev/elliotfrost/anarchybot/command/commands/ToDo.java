package dev.elliotfrost.anarchybot.command.commands;

import dev.elliotfrost.anarchybot.Bot;
import dev.elliotfrost.anarchybot.command.CommandContext;
import dev.elliotfrost.anarchybot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.List;

public class ToDo implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        Bot.getDatabaseManager().getDatabaseToDo().getToDoList(ctx.getChannel().getId());
        String query = "SELECT * FROM todols WHERE channelid = " + ctx.getChannel().getId() + ";";
        String todo, done, id; // Channel ID needs to correlate to the message ID of the embed
        switch (args.get(0)) {
            case "call":
                todo = " ";
                done = " ";
                break;
            case "add":
                break;
            case "finish":
                break;
            case "abandon":
                break;
            default:
                ctx.getChannel().sendMessage("You cannot use todo by itself, you must assign a task to it. Either `add`," +
                        "`finish`, or `abandon`.").queue();
        }
        MessageEmbed embed = new EmbedBuilder()
                .setTitle("To do list for " + ctx.getChannel().getAsMention())
                .addField("To-Do", "todo", false)
                .addField("Done", "done", false)
                .build();
    }

    @Override
    public String getName() {
        return "todo";
    }

    @Override
    public List<String> getAliases() {
        return List.of("td");
    }
}
