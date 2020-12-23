package doscord.listener;

import doscord.config.Config;
import doscord.tools.commandProcessing.CommandHandler;
import doscord.tools.commandProcessing.CommandParser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Commander extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String prefix = Config.get("prefix");

        if (event.getMessage().getContentRaw().startsWith(prefix)
                && !Objects.equals(event.getMessage().getAuthor().getId(), event.getJDA().getSelfUser().getId())
                && !event.getMessage().getAuthor().isBot()) {
            try {
                CommandHandler.handleCommand(CommandParser.parser(event.getMessage().getContentRaw().toLowerCase(), event, prefix));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
