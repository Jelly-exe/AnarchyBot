package dev.elliotfrost.anarchybot.listeners;

import com.coreoz.wisp.Scheduler;
import com.coreoz.wisp.SchedulerConfig;
import com.coreoz.wisp.schedule.Schedules;
import dev.elliotfrost.anarchybot.Scheduled.ServerStatus;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Listener extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);
    private final CommandManager manager = new CommandManager();

    @Override
    public void onReady(ReadyEvent event) {
        LOGGER.info("{} is ready", event.getJDA().getSelfUser().getAsTag());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Give JDA time to finish loading before execution of statuses
        new ServerStatus().newStatuses();
        new Scheduler(
                SchedulerConfig
                        .builder()
                        .minThreads(1)
                        .maxThreads(2)
                        .threadsKeepAliveTime(Duration.ofSeconds(5))
                        .build()
        ).schedule(new ServerStatus(), Schedules.fixedDelaySchedule(Duration.ofSeconds(10)));
    }
}
