package doscord.commands;

import doscord.tools.Screen;
import doscord.tools.ScreenBuilder;
import doscord.tools.commandProcessing.Command;
import doscord.tools.handlers.CommandOutputHandler;
import doscord.tools.states.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
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
        Guild.baseLocation(event.getGuild().getName(), event.getAuthor().getId());
        String location;

        location = screen.get("location");

        String returning;

        File folder = new File(System.getProperty("user.dir") + "\\screens\\drive\\" + location.replace("U:\\", "") + "\\");
        File[] listOfFiles = folder.listFiles();

        StringBuilder dirs = new StringBuilder();
        StringBuilder files = new StringBuilder();
        try {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    files.append(listOfFiles[i].getName() + "\n");
                } else if (listOfFiles[i].isDirectory()) {
                    dirs.append(listOfFiles[i].getName() + "\\\n");
                }
            }
            returning = dirs.append(files.toString()).toString();
        } catch (Exception e) {
            returning = "This Folder is empty.";
        }

        List<String> commands = new ArrayList<>();
        commands.add(CommandOutputHandler.commandOutput("ls", returning));

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
