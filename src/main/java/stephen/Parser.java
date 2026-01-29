package stephen;

/**
 * A class represents the parser for interpreting user commands.
 */
public class Parser {
    public Parser() {}
    /**
     * Method to parse user input into a command.
     */
    public Command parse(String input) {
        return Command.command(input);
    }
}
