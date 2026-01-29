package stephen.exception;

/**
 * Throws an exception when the input is invalid.
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
