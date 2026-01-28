package stephen;

/**
 * Exception thrown when the number provided is invalid or out of bounds.
 */
public class InvalidNumberException extends RuntimeException {
    public InvalidNumberException(String message) {
        super(message);
    }
}
