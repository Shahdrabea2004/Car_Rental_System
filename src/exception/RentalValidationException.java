package exception;

/**
 * Exception thrown when rental data is invalid.
 */
public class RentalValidationException extends RuntimeException {

    /**
     * Creates a new RentalValidationException
     * with the specified error message.
     *
     * @param message description of the validation error
     */
    public RentalValidationException(String message) {
        super(message);
    }
}