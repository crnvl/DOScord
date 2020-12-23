package doscord.tools;

public class Directory {

    public static String sub(String userId, String location, String move) {

        Screen screen = new Screen(userId);
        String loc;

        switch (move) {
            default -> {
                if (!location.contains(move)) {
                    loc = location + "\\" + move;
                    screen.load();
                    screen.save("location", loc);
                    screen.close();
                } else {
                    loc = location;
                    screen.load();
                    screen.save("location", loc);
                    screen.close();
                }
            }
            case "../" -> {
                StringBuilder builder = new StringBuilder();
                String[] dir = location.split("\\\\");
                if (dir.length >= 3) {
                    for (int i = 0; i < dir.length - 1; i++) {
                        if (i != 0)
                            builder.append("\\" + dir[i]);
                        else
                            builder.append(dir[i]);
                    }
                    loc = builder.toString();
                } else {
                    loc = dir[0] + "\\" + dir[1];
                }


                screen.load();
                screen.save("location", loc);
                screen.close();
            }
        }
        return loc;
    }

}
