package doscord.tools;

public class Response {

    String comm;
    String s;

    public Response(String command) {
        comm = command;
    }

    public String getMessage(String userId, String ResponseState) {
        s = ResponseState;
        Screen screen = new Screen(userId);
        screen.load();
        switch (comm) {
            case "cd":
                switch (s) {
                    case "valid":
                        return "> Moved to " + screen.get("location");
                    case "invalid":
                        return "> " + screen.get("location");
                    case "debug":
                        return "debug";
                }
                break;
            default:
                return "Invalid Command Selected";
        }
        return "Invalid Command Selected";
    }

}
