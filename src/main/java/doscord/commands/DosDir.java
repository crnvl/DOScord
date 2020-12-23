package doscord.commands;

import doscord.tools.Screen;
import doscord.tools.ScreenBuilder;
import doscord.tools.commandProcessing.Command;
import doscord.tools.handlers.CommandOutputHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class DosDir implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

        Screen screen = new Screen(event.getAuthor().getId());
        screen.load();
        String location;
        if (!screen.isReal("location")) {
            screen.save("location", "U:\\" + event.getGuild().getName());
            screen.close();
        }

        location = screen.get("location");

        String returning;
        if (location.contains("channels")) {
            int max = 10;
            boolean more = false;
            int channel;

            if (max > event.getGuild().getTextChannels().size())
                max = event.getGuild().getTextChannels().size();
            else
                more = true;

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < max; i++) {
                builder.append("#" + event.getGuild().getTextChannels().get(i).getName() + " (" + event.getGuild().getTextChannels().get(i).getId() + ")\n");
            }
            if (more) {
                channel = event.getGuild().getTextChannels().size() - max;
                builder.append("And " + channel + " more...");
            }

            returning = builder.toString();
        } else {
            returning = "This Folder is empty.";
        }


        List<String> commands = new ArrayList<>();
        commands.add(CommandOutputHandler.commandOutput("dir", returning));

        ScreenBuilder screenBuilder = new ScreenBuilder(location, commands);
        event.getTextChannel().sendMessage(screenBuilder.getWindow()).queue();

    }

    @Override
    public String Description() {
        return null;
    }

    @Override
    public String Example() {
        return null;
    }

    @Override
    public String Usage() {
        return null;
    }

    @Override
    public String Permissions() {
        return null;
    }
}
