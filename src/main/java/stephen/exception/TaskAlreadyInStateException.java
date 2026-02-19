package stephen.exception;

/**
 * Throws an exception when a task is already in the desired state.
 */
public class TaskAlreadyInStateException extends RuntimeException {
    public TaskAlreadyInStateException(String message) {
        super(message);
    }
}
