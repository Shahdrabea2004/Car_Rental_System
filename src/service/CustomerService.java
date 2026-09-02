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

    /**
     * Stores all registered customers in the system.
     */
    private final List<Customer> customers;

    /**
     * Creates a CustomerService using the provided customer list.
     *
     * @param customers the list of registered customers
     */
    public CustomerService(List<Customer> customers) {
        this.customers = customers;
    }


    /**
     * Validates that the customer ID is unique in the system.
     *
     * @param customer customer whose ID will be checked
     * @throws CustomerServiceException if the customer ID already exists
     */
    private void validateCustomerId(Customer customer) {
        if (customer == null) {
            throw new CustomerServiceException(
                    "Customer cannot be null."
            );
        }
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
     * @throws CustomerServiceException if the customer is null
     * or the customer ID already exists
     */
    public void registerCustomer(Customer customer) {
        validateCustomerId(customer);
        customers.add(customer);
    }

    /**
     * Searches for a customer by their ID.
     *
     * @param customerId the ID of the customer to search for
     * @return the matching customer if found, otherwise null
     */
    public Customer searchCustomer(String customerId) {

        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }

        return null;
    }
}