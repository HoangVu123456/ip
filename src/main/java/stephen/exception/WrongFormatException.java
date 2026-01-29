package stephen.exception;

/**
 * Exception thrown when the command format does not match the expected pattern.
 */
public class WrongFormatException extends RuntimeException {
    public WrongFormatException(String message) {
        super(message);
    }
}
