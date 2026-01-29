package stephen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Handles the user interface for client interactions.
 */
public class Ui {
    private BufferedReader reader;

    public Ui() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        println("Hello! I'm Stephen");
        println("What can I do for you?");
        showLine();
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showGoodbye() {
        showLine();
        println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays the line separator.
     */
    public void showLine() {
        println("____________________________________________________________");
    }

    /**
     * Reads a command from the user.
     */
    public String readCommand() throws IOException {
        return reader.readLine();
    }

    /**
     * Prints a message on the interface.
     */
    public void println(String message) {
        System.out.println(message);
    }

    /**
     * Shows an error message to the user.
     */
    public void showError(String message) {
        println(message);
    }

    /**
     * Closes the reader resource.
     */
    public void close() throws IOException {
        reader.close();
    }
}
