package doscord.commands;

import com.google.common.io.Files;
import doscord.tools.Screen;
import doscord.tools.ScreenBuilder;
import doscord.tools.commandProcessing.Command;
import doscord.tools.handlers.CommandOutputHandler;
import doscord.tools.states.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DosFile implements Command {
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
        if (args.length >= 1) {
            File file = new File(System.getProperty("user.dir") + "/screens\\drive\\" + location.replace("U:\\", "") + "\\" + args[0] + ".dcScript");
            try {
                if (!file.exists()) {
                    Files.createParentDirs(file);
                    Files.touch(file);
                    returning = "Saved " + location.replace("U:\\", "") + "\\" + args[0] + ".dcScript";
                } else {
                    if (args.length >= 2) {
                        PrintWriter writer = new PrintWriter(file, "UTF-8");
                        StringBuilder script = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            script.append(args[i] + " ");
                        }
                        writer.write(script.toString());
                        writer.close();
                        returning = "Edited " + location.replace("U:\\", "") + "\\" + args[0] + ".dcScript";
                    } else {
                        returning = "Failed to create " + location + "\\" + args[0] + ".dcScript" + "\n" + "File already exists!";
                    }
                }

            } catch (IOException e) {
                returning = "Failed to create " + location + "\\" + args[0] + ".dcScript" + "\n" + e.getMessage();
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
