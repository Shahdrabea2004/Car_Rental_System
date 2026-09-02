package service;

import exception.CustomerServiceException;
import model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for managing customers
 * in the car rental system.
 *
 * This class provides operations related to customer registration
 * and maintains a list of all registered customers.
 */
public class CustomerService {

    // Stores all registered customers in the system.
    private final static List<Customer> customers = new ArrayList<>();


    /**
     * Validates that the customer ID is unique in the system.
     *
     * @param customer customer whose ID will be checked
     * @throws CustomerServiceException if the customer ID already exists
     */
    private static void validateCustomerId(Customer customer) {
        for (Customer existingCustomer : customers) {
            if (existingCustomer.getId().equals(customer.getId())) {
                throw new CustomerServiceException(
                        "Customer ID already exists."
                );
            }
        }
    }

    /**
     * Registers a new customer in the car rental system.
     *
     * @param customer the customer to be registered
     */
    public static void registerCustomer(Customer customer) {
        validateCustomerId(customer);
        customers.add(customer);
    }


}
