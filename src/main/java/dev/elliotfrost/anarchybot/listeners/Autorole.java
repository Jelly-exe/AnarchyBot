package dev.elliotfrost.anarchybot.listeners;

import dev.elliotfrost.anarchybot.Config;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Autorole extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Roles.class);
    private final CommandManager manager = new CommandManager();

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        System.out.println("autorole");
        Role role = null;
        if (!event.getUser().isBot()) {
            role = event.getGuild().getRoleById(Config.get("MEMBER_ROLE"));
        } else if (event.getUser().isBot()) {
            role = event.getGuild().getRoleById(Config.get("BOT_ROLE"));
        }
        assert role != null;
        event.getGuild().addRoleToMember(event.getUser().getIdLong(), role).queue();
    }
}
