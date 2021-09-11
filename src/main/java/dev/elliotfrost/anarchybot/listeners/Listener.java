package dev.elliotfrost.anarchybot.listeners;

import dev.elliotfrost.anarchybot.Bot;
import dev.elliotfrost.anarchybot.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;
import net.dv8tion.jda.api.requests.restaction.pagination.ReactionPaginationAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.util.Objects;

public class Listener extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);
    private final CommandManager manager = new CommandManager();

    @Override
    public void onReady(ReadyEvent event) {
        LOGGER.info("{} is ready", event.getJDA().getSelfUser().getAsTag());
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (event.getChannel().getId().equals(Config.get("SUGGESTIONS_CHANNEL"))) {
            try {
                String reaction;

                if (event.getReactionEmote().getEmoji().equals("\u2705")) {
                    reaction = "\u274C";
                } else if (event.getReactionEmote().getEmoji().equals("\u274C")) {
                    reaction = "\u2705";
                } else {
                    event.getReaction().removeReaction(event.getUser()).queue();
                    return;
                }
                ReactionPaginationAction reactions = event.getChannel().retrieveReactionUsersById(event.getMessageId(), reaction);

                reactions.forEachAsync((user) -> {
                    if (user.equals(event.getUser()) && !user.isBot()) {
                        event.getReaction().removeReaction(user).queue();
                        return true;
                    }

                    return false;
                });
            } catch (Exception e) {
                event.getReaction().removeReaction(event.getUser()).queue();
            }
        }

    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("test1")) {
            event.reply("Testing")
                    .addActionRow(Button.primary("test", "Test"))
                    .queue();
        }
    }

//    @Override
//    public void onButtonClick(ButtonClickEvent event) {
//        if (event.getComponentId().equals("button:test")) {
//            event.reply("Test").setEphemeral(true).queue();
//        }
//    }

//    @Override
//    public void onSelectionMenu(SelectionMenuEvent event) {
//        if (event.getComponentId().equals("menu:tickets")) {
//            List<String> values = event.getValues();
//
//            event.reply("Creating the ticket goes here. Selected Value: `" + values.get(0) + "`")
//                    .setEphemeral(true)
//                    .queue();
//        }
//    }

    @Override
    public void onSelectionMenu(SelectionMenuEvent event) {
        if (event.getComponentId().equals("menu:tickets")) {
            SelectionMenu menu = SelectionMenu.create("menu:tickets")
                    .setPlaceholder("Select a reason...")
                    .addOption("Server Bug / Issue", "bug", "Report any bugs or issues with the minecraft server.", Emoji.fromMarkdown("\uD83D\uDC1B"))
                    .addOption("Player Report", "report", "Report a players actions (discord or server).", Emoji.fromMarkdown("\uD83D\uDD12"))
                    .addOption("Support", "support", "Get help with anything server related.", Emoji.fromMarkdown("\u2753"))
                    .addOption("Other", "other", "Anything which doesnt fall into an above category.", Emoji.fromMarkdown("\uD83D\uDCF0"))
                    .build();

            event.getMessage().editMessageComponents(ActionRow.of(menu)).queue();
            Statement statement;
            ResultSet result;
            int ticketNum = Bot.getDatabaseManager().getDatabaseTickets().getTicketNumber(event.getUser().getId());

            String user = event.getUser().getName() + "#" + event.getUser().getDiscriminator();

            String title, descr, java, br, icon;
            Color color;

            switch (event.getValues().get(0)) {
                case "bug" -> {
                    color = Color.RED;
                    descr = "A bug report";
                    java = "testjavausername";
                    br = "testbrusername";
                }
                case "report" -> {
                    color = Color.lightGray;
                    descr = "A Player report";
                    java = "testjavausername";
                    br = "testbrusername";
                }
                case "support" -> {
                    color = Color.GREEN;
                    descr = "General Support";
                    java = "testjavausername";
                    br = "testbrusername";
                }
                default -> {
                    color = Color.GREEN;
                    descr = "Other things you may need to talk to the admin for";
                    java = "testjavausername";
                    br = "testbrusername";
                }
            }
            // ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
            MessageEmbed embed = new EmbedBuilder()
                    .setTitle("New Ticket - Opened by " + user)
                    .setDescription(descr)
                    .addField("Java IGN:", java, true)
                    .addField("Bedrock IGN:", br, true)
                    .setTimestamp(Instant.now())
                    .setColor(color)
                    .setThumbnail("https://i.imgur.com/i4ht6nZ.png")
                    .setFooter("Anarchy Bot v2.0 | skynodeanarchy.ciputin.cf", "https://i.imgur.com/i4ht6nZ.png")
                    .build();

            Objects.requireNonNull(event.getGuild()).createTextChannel(user + "-" + ticketNum, event.getGuild().getCategoryById("867535554253684807"))
                        .addMemberPermissionOverride(event.getUser().getIdLong(), 3072, 0)
                        .addRolePermissionOverride(Objects.requireNonNull(event.getGuild().getRoleById("866757654244622366")).getIdLong(), 0 ,3072)
                        .setTopic(user + "'s support ticket || has had " + ticketNum + " ticket(s)")
                        .queue(channel -> {
                            channel.sendMessage("<@" + event.getUser().getId() + "> <@&867133159137214514>").setEmbeds(embed).setActionRow(Button.danger("button:close", "Close Ticket"), Button.success("button:transcript", "Generate Transcript")).queue(message -> {
                                    channel.pinMessageById(message.getId());
                                    }
                            );
                            event.reply("Your ticket is: <#" + channel.getId() + ">")
                                    .setEphemeral(true)
                                    .queue();
                        });

                // DB Code for adding a ticket to user's total here
                // DB code for getting a user's info (linked accts etc)
        }
    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        if (event.getComponentId().equals("button:close")) {
            event.getGuildChannel().delete().queue();
        } else if (event.getComponentId().equals("button:transcript")) {
            event.reply("This feature is currently disabled.")
                    .setEphemeral(true)
                    .queue();
        }
    }
}
