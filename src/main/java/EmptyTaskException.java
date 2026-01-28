package stephen;

/**
 * Exception thrown when the command lack essential information about the task.
 */
public class EmptyTaskException extends RuntimeException {
    public EmptyTaskException(String message) {
        super(message);
    }
}
