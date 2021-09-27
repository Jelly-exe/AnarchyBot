package dev.elliotfrost.anarchybot.listeners;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

public class Autorole extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Roles.class);
    private final CommandManager manager = new CommandManager();

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        if (event.getUser().isBot() == false) {
            Role role = event.getGuild().getRoleById("875032095561482270");
            event.getGuild().addRoleToMember(event.getMember().getId(), role);
        } else if (event.getUser().isBot() == true) {
            Role bot = event.getGuild().getRoleById("866762499088318535");
            event.getGuild().addRoleToMember(event.getMember().getId(), bot);
        }
    }
}
