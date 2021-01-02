package doscord.commands;

import doscord.tools.Screen;
import doscord.tools.ScreenBuilder;
import doscord.tools.commandProcessing.Command;
import doscord.tools.commandProcessing.CommandHandler;
import doscord.tools.handlers.CommandOutputHandler;
import doscord.tools.states.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.*;
import java.util.*;

public class DosStart implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        Screen screen = new Screen(event.getAuthor().getId());
        Guild.baseLocation(event.getGuild().getName(), event.getAuthor().getId());
        String location;

        location = screen.get("location");

        String returning;
        HashMap<String, String[]> fileCommands = new HashMap<>();
        if (args.length >= 1) {
            File file = new File(System.getProperty("user.dir") + "\\screens\\drive\\" + location.replace("U:\\", "") + "\\" + args[0] + ".dcScript");
            if (file.exists()) {
                String content = null;
                try {
                    content = new Scanner(file).useDelimiter("\\Z").next();

                    System.out.println(content);

                    String allLines[] = content.split("\\r?\\n");

                    for (int i = 0; i < allLines.length; i++) {
                        String[] invoke = allLines[i].split(" ");
                        String[] commandArgs = Arrays.copyOfRange(invoke, 1, invoke.length);

                        System.out.println(invoke[0] + " | " + Arrays.toString(commandArgs));
                        CommandHandler.commands.get(invoke[0]).action(commandArgs, event);
                    }
                    returning = "Executed " + location.replace("U:\\", "") + "\\" + args[0] + ".dcScript";
                } catch (FileNotFoundException e) {
                    returning = "File doesn't exist!";
                }
            } else {
                returning = "File doesn't exist!";
            }
        } else {
            returning = "No Filename specified";
        }

        List<String> commands = new ArrayList<>();
        commands.add(CommandOutputHandler.commandOutput("file", returning));
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
