package doscord;

import doscord.commands.DosCD;
import doscord.commands.DosDir;
import doscord.commands.DosFile;
import doscord.commands.DosStart;
import doscord.config.Config;
import doscord.listener.Commander;
import doscord.tools.commandProcessing.CommandHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Main {

        public static JDA jda;

    public static void main(String[] args) throws LoginException {

        Config.load();

        String token = Config.get("token");

        JDABuilder builder = JDABuilder.createDefault(token);
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setCompression(Compression.NONE);

        builder.addEventListeners(new Commander());

        builder.build();
        Commands();
    }

    public static void Commands() {
        CommandHandler.commands.put("cd", new DosCD());
        CommandHandler.commands.put("ls", new DosDir());
        CommandHandler.commands.put("file", new DosFile());
        CommandHandler.commands.put("start", new DosStart());
    }

}
