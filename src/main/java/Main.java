import com.google.gson.Gson;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, IOException {
        Gson gson = new Gson();

        Reader reader = Files.newBufferedReader(Paths.get("bot.json"));
        Reader reader2 = Files.newBufferedReader(Paths.get("config.json"));

        Bot bot = gson.fromJson(reader, Bot.class);
        Config config = gson.fromJson(reader2, Config.class);

        reader.close();
        reader2.close();

        JDABuilder.createLight(bot.token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new Main())
                .setActivity(Activity.playing("I am a dev bot, fuck you :)"))
                .build();
    }

    public void onMessageReceived(@NotNull MessageReceivedEvent event)
    {
        String prefix = config.prefix;
        if (bot.dev) { prefix = prefix + prefix; }
        Message msg = event.getMessage();
        if (msg.getContentRaw().equals(prefix + "ciisgay") || msg.getContentRaw().equals(prefix + "ping"))
        {
            MessageChannel channel = event.getChannel();
            long time = System.currentTimeMillis();
            channel.sendMessage("Pong!") /* => RestAction<Message> */
                    .queue(response /* => Message */ -> {
                        response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                    });
        }

        else if (msg.getContentRaw().equals(prefix + "membercount")) {
            MessageChannel channel = event.getChannel();
            int memberCount = event.getGuild().getMemberCount();
            channel.sendMessage("There is " + memberCount + " members in the Anarchy Server.").queue(response -> {});
        }

        else if (msg.getContentRaw().equals(prefix + "help")) {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("This is a very unhelpful message").queue(response -> {});
        }
    }
}
