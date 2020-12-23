package doscord.commands;

import doscord.tools.Directory;
import doscord.tools.Response;
import doscord.tools.Screen;
import doscord.tools.ScreenBuilder;
import doscord.tools.commandProcessing.Command;
import doscord.tools.handlers.CommandOutputHandler;
import doscord.tools.states.ResponseState;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class DosCD implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        Screen screen = new Screen(event.getAuthor().getId());
        screen.load();
        String location;
        if (!screen.isReal("location")) {
            screen.save("location", "U:\\" + event.getGuild().getName());
            screen.close();
        }

        location = screen.get("location");

        String newLoc, cmd, rsp;
        Response response = new Response("cd");
        if (args.length >= 1) {
            cmd = "cd " + args[0];
            newLoc = Directory.sub(event.getAuthor().getId(), location, args[0]);
            rsp = response.getMessage(event.getAuthor().getId(), ResponseState.VALID);
        } else {
            cmd = "cd";
            newLoc = location;
            rsp = response.getMessage(event.getAuthor().getId(), ResponseState.INVALID);
        }

        List<String> commands = new ArrayList<>();
        if (screen.isReal("commands")) {
            commands.add(screen.get("commands"));
            commands.add(CommandOutputHandler.commandOutput(cmd, rsp));
        } else
            commands.add(CommandOutputHandler.commandOutput(cmd, rsp));

        screen.save("location", newLoc);
        screen.close();
        ScreenBuilder screenBuilder = new ScreenBuilder(newLoc, commands);

        event.getTextChannel().sendMessage(screenBuilder.getWindow()).queue();
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
