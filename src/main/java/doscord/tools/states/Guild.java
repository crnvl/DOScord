package doscord.tools.states;

import doscord.tools.Screen;

public class Guild {

    public static void baseLocation(String guildName, String userId) {
        Screen screen = new Screen(userId);
        screen.load();
        String location;
        if (!screen.isReal("location") || !screen.get("location").contains(guildName)) {
            screen.save("location", "U:\\" + guildName);
            screen.close();
        }
    }

}
