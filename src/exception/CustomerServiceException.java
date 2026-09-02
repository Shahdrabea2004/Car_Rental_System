package exception;

/**
 * Exception thrown when a customer-related service operation fails.
 */
public class CustomerServiceException extends RuntimeException {

    /**
     * Creates a new CustomerServiceException with the specified message.
     *
     * @param message error message
     */
    public CustomerServiceException(String message) {
        super(message);
    }
}