package stephen;

/**
 * Represents different commands that can be used to communicate with the chatbot.
 */
public enum Command {
    LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, BYE, UNKNOWN;

    /**
     * Returns the Command enum corresponding to what the user input.
     */
    public static Command command(String input) {
        assert input != null : "Command input should not be null";
        if (input.equals("list")) {
            return LIST;
        } else if (input.startsWith("mark ")) {
            return MARK;
        } else if (input.startsWith("unmark ")) {
            return UNMARK;
        } else if (input.startsWith("delete ")) {
            return DELETE;
        } else if (input.startsWith("todo ")) {
            return TODO;
        } else if (input.startsWith("deadline ")) {
            return DEADLINE;
        } else if (input.startsWith("event ")) {
            return EVENT;
        } else if (input.startsWith("find ")) {
            return FIND;
        } else if (input.equals("bye")) {
            return BYE;
        } else {
            return UNKNOWN;
        }
    }
}
