package stephen.exception;

/**
 * Throws an exception when the format of the input is wrong.
 */
public class WrongFormatException extends RuntimeException {
    public WrongFormatException(String message) {
        super(message);
    }
}
