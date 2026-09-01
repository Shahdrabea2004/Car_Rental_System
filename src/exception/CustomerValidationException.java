package exception;

/**
 * Exception thrown when customer data is invalid.
 *
 * This exception is used to report validation errors
 * related to customer information.
 */
public class CustomerValidationException extends RuntimeException {

    /**
     * Creates a new CustomerValidationException
     * with the specified error message.
     *
     * @param message description of the validation error
     */
    public CustomerValidationException(String message) {
        super(message);
    }
}
