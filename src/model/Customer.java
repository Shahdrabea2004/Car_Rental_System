package model;

import exception.CustomerValidationException;

import java.util.List;

/**
 * Represents a customer in the car rental system.
 *
 * A customer has personal contact information and a complete
 * history of all rentals made by the customer.
 */
public class Customer {

    private final String id;
    private final String fullName;
    private String email;
    private String phoneNumber;
    private final List<Rental> historyRentals;


    /**
     * Creates a new customer after validating the provided information.
     *
     * @param id unique identifier of the customer
     * @param fullName customer's full name
     * @param email customer's email address
     * @param phoneNumber customer's phone number
     * @param historyRentals customer's rental history
     */
    public Customer(String id, String fullName, String email, String phoneNumber, List<Rental> historyRentals) {
        validateId(id);
        validateFullName(fullName);
        validateEmail(email);
        validatePhoneNumber(phoneNumber);
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.historyRentals = historyRentals;
    }

    /**
     * Validates the customer's ID.
     *
     * @param id customer ID to validate
     * @throws CustomerValidationException if the ID is invalid
     */
    private void validateId(String id) {
        if (id == null || id.isBlank()) {
            throw new CustomerValidationException(
                    "Customer ID cannot be empty."
            );
        }

        if (id.length() != 14) {
            throw new CustomerValidationException(
                    "Customer ID must contain exactly 14 characters."
            );
        }
    }

    /**
     * Validates the customer's full name.
     *
     * @param fullName name to validate
     * @throws CustomerValidationException if the name is invalid
     */
    private void validateFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new CustomerValidationException(
                    "Full name cannot be empty."
            );
        }

        if (fullName.length() < 3) {
            throw new CustomerValidationException(
                    "Full name must contain at least 3 characters."
            );
        }
    }

    /**
     * Validates the customer's email address.
     *
     * @param email email to validate
     * @throws CustomerValidationException if the email is invalid
     */
    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new CustomerValidationException(
                    "Email cannot be empty."
            );
        }

        if (!email.contains("@")) {
            throw new CustomerValidationException(
                    "Invalid email format."
            );
        }

    }

    /**
     * Validates the customer's phone number.
     *
     * @param phoneNumber phone number to validate
     * @throws CustomerValidationException if the phone number is invalid
     */
    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new CustomerValidationException(
                    "Phone number cannot be empty."
            );
        }

        if (phoneNumber.length() != 11) {
            throw new CustomerValidationException(
                    "Phone Number must contain exactly 11 characters."
            );
        }
    }


    /**
     * Returns the customer's unique ID.
     *
     * @return customer ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the customer's full name.
     *
     * @return customer full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Returns the customer's email.
     *
     * @return customer email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Updates the customer's email after validation.
     *
     * @param email new email address
     */
    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    /**
     * Returns the customer's phone number.
     *
     * @return customer phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Updates the customer's phone number after validation.
     *
     * @param phoneNumber new phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the customer's complete rental history.
     *
     * @return list of all rentals made by the customer
     */
    public List<Rental> getHistoryRentals() {
        return historyRentals;
    }

    /**
     * Adds a rental to the customer's rental history.
     *
     * @param rental rental to add to the history
     */
    public void addRental(Rental rental) {
        historyRentals.add(rental);
    }

}
