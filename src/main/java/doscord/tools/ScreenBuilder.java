package doscord.tools;

import java.util.List;

public class ScreenBuilder {

    String loc;
    List<String> comms;

    public ScreenBuilder(String location, List<String> commands) {
        loc = location;
        comms = commands;
    }

    public String getWindow() {
        StringBuilder window = new StringBuilder();

        window.append("```md\n");

        if (comms != null) {
            for (int i = 0; i < comms.size(); i++) {
                window.append(loc + ">" + comms.get(i).replaceAll("=", "\n") + "\n");
            }
        }

        window.append("\n```");

        return window.toString();
    }

}
