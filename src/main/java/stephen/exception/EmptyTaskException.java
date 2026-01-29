package stephen.exception;

/**
 * Throws an exception when a task description is empty.
 */
public class EmptyTaskException extends RuntimeException {
    public EmptyTaskException(String message) {
        super(message);
    }
}
