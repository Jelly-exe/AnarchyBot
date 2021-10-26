package dev.elliotfrost.anarchybot.listeners;

import com.coreoz.wisp.Scheduler;
import com.coreoz.wisp.schedule.Schedules;
import dev.elliotfrost.anarchybot.Scheduled.ServerStatus;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Listener extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);
    private final CommandManager manager = new CommandManager();

    @Override
    public void onReady(ReadyEvent event) {
        LOGGER.info("{} is ready", event.getJDA().getSelfUser().getAsTag());
        new ServerStatus().newStatuses();
        new Scheduler().schedule(new ServerStatus(), Schedules.fixedDelaySchedule(Duration.ofSeconds(2)));
    }
}
