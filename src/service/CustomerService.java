package service;

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
     * Registers a new customer in the car rental system.
     *
     * @param customer the customer to be registered
     */
    public static void registerCustomer(Customer customer) {
        customers.add(customer);
    }
}
