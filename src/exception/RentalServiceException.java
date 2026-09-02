package exception;

/**
 * Exception thrown when a rental service operation cannot be completed.
 */
public class RentalServiceException extends RuntimeException {

    public RentalServiceException(String message) {
        super(message);
    }
}