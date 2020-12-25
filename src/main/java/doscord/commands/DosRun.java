package doscord.commands;

import doscord.tools.Screen;
import doscord.tools.ScreenBuilder;
import doscord.tools.commandProcessing.Command;
import doscord.tools.handlers.CommandOutputHandler;
import doscord.tools.states.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DosRun implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        String returning;

        Screen screen = new Screen(event.getAuthor().getId());
        Guild.baseLocation(event.getGuild().getName(), event.getAuthor().getId());
        String location;
        location = screen.get("location");

        if(args.length >= 1) {
            // Execute command
            String[] command = Arrays.copyOfRange(args, 0, args.length);
            String commandInvoke = Arrays.toString(command);

            //execute command
            returning = "";
        }else
            returning = "No Command specified.";

        List<String> commands = new ArrayList<>();
        commands.add(CommandOutputHandler.commandOutput("run", returning));
        ScreenBuilder builder = new ScreenBuilder(location, commands);
        event.getTextChannel().sendMessage(builder.getWindow()).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

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
