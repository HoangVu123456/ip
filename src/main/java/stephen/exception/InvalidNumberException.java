package stephen.exception;

/**
 * Throws an exception when the input value is invalid.
 */
public class InvalidNumberException extends RuntimeException {
    public InvalidNumberException(String message) {
        super(message);
    }
}
