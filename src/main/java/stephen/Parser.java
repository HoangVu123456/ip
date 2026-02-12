package stephen;

/**
 * Parses user input into commands.
 */
public class Parser {
    public Parser() {}
    /**
     * Parses the user input and returns the corresponding Command.
     */
    public Command parse(String input) {
        assert input != null : "User input should not be null";
        return Command.command(input);
    }
}
