package exception;

/**
 * Exception thrown when an invalid car status transition is attempted.
 */
public class CarStatusException extends Exception {

    /**
     * Constructs a CarStatusException with the specified error message.
     *
     * @param message description of the invalid status transition
     */
    public CarStatusException(String message) {
        super(message);
    }
}
