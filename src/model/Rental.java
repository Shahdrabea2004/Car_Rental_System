package model;

import exception.RentalValidationException;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * Represents a rental transaction between a customer and a car.
 * <p>
 * A Rental stores information about the customer, rented car,
 * rental dates, return date, final price, and rental status.
 */
public class Rental {
    private final String id;
    private final Customer customer;
    private final Car car;
    private final LocalDate dateTheRentalBegins;
    private final LocalDate dateExpectedBack;
    private LocalDate dateActuallyReturned;
    private BigDecimal finalPrice;
    private RentalStatuses rentalStatuses;


    /**
     * Creates a new rental.
     * <p>
     * A newly created rental starts with ACTIVE status.
     * The actual return date and final price are initially null.
     *
     * @param id                  unique rental identifier
     * @param customer            customer renting the car
     * @param car                 car being rented
     * @param dateTheRentalBegins rental start date
     * @param dateExpectedBack    expected return date
     */
    public Rental(String id, Customer customer, Car car, LocalDate dateTheRentalBegins, LocalDate dateExpectedBack) {

        validateId(id);
        validateCustomer(customer);
        validateCar(car);
        validateRentalDates(dateTheRentalBegins, dateExpectedBack);
        this.id = id;
        this.customer = customer;
        this.car = car;
        this.dateTheRentalBegins = dateTheRentalBegins;
        this.dateExpectedBack = dateExpectedBack;
        this.dateActuallyReturned = null;
        this.finalPrice = null;
        this.rentalStatuses = RentalStatuses.ACTIVE;

    }


    /**
     * Validates the rental ID.
     *
     * @param id rental ID to validate
     * @throws RentalValidationException if the ID is null or blank
     */
    private void validateId(String id) {
        if (id == null || id.isBlank()) {
            throw new RentalValidationException("Rental ID cannot be null or blank.");
        }
    }

    /**
     * Validates the customer.
     *
     * @param customer customer to validate
     * @throws RentalValidationException if the customer is null
     */
    private void validateCustomer(Customer customer) {
        if (customer == null) {
            throw new RentalValidationException("Customer cannot be null.");
        }
    }

    /**
     * Validates the car.
     *
     * @param car car to validate
     * @throws RentalValidationException if the car is null
     */
    private void validateCar(Car car) {
        if (car == null) {
            throw new RentalValidationException("Car cannot be null.");
        }
    }

    /**
     * Validates the rental dates.
     * <p>
     * The expected return date must be after the rental start date.
     *
     * @param dateTheRentalBegins rental start date
     * @param dateExpectedBack    expected return date
     * @throws RentalValidationException if the dates are invalid
     */
    private void validateRentalDates(
            LocalDate dateTheRentalBegins,
            LocalDate dateExpectedBack) {

        if (dateTheRentalBegins == null) {
            throw new RentalValidationException("Rental start date cannot be null.");
        }

        if (dateExpectedBack == null) {
            throw new RentalValidationException("Expected return date cannot be null.");
        }

        if (!dateExpectedBack.isAfter(dateTheRentalBegins)) {
            throw new RentalValidationException(
                    "Expected return date must be after rental start date."
            );
        }

    }


    /**
     * Returns the unique rental ID.
     *
     * @return rental ID
     */
    public String getId() {
        return id;
    }


    /**
     * Returns the customer associated with this rental.
     *
     * @return customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Returns the car associated with this rental.
     *
     * @return rented car
     */
    public Car getCar() {
        return car;
    }

    /**
     * Returns the rental start date.
     *
     * @return rental start date
     */
    public LocalDate getDateTheRentalBegins() {
        return dateTheRentalBegins;
    }

    /**
     * Returns the expected return date.
     *
     * @return expected return date
     */
    public LocalDate getDateExpectedBack() {
        return dateExpectedBack;
    }

    /**
     * Returns the actual return date.
     *
     * @return actual return date, or null if the rental is still active
     */
    public LocalDate getDateActuallyReturned() {
        return dateActuallyReturned;
    }


    /**
     * Updates the rental status.
     *
     * @param rentalStatuses new rental status
     */
    public void setRentalStatuses(RentalStatuses rentalStatuses) {
        this.rentalStatuses = rentalStatuses;
    }

    /**
     * Returns the current rental status.
     *
     * @return rental status
     */
    public RentalStatuses getRentalStatuses() {
        return rentalStatuses;
    }

    /**
     * Sets the actual return date.
     *
     * The actual return date cannot be null and cannot be
     * before the rental start date.
     *
     * @param dateActuallyReturned actual date the car was returned
     * @throws RentalValidationException if the date is null or before the rental start date
     */
    public void setDateActuallyReturned(LocalDate dateActuallyReturned) {
        if (dateActuallyReturned == null) {
            throw new RentalValidationException(
                    "Actual return date cannot be null."
            );
        }

        if (dateActuallyReturned.isBefore(dateTheRentalBegins)) {
            throw new RentalValidationException(
                    "Actual return date cannot be before rental start date."
            );
        }

        this.dateActuallyReturned = dateActuallyReturned;
    }

    /**
     * Returns the final rental price.
     *
     * @return final price, or null if the price has not been calculated yet
     */
    public BigDecimal getFinalPrice() {
        return finalPrice;
    }


    /**
     * Sets the final calculated rental price.
     *
     * @param finalPrice final rental price
     * @throws RentalValidationException if the final price is null or negative
     */
    public void setFinalPrice(BigDecimal finalPrice) {
        if (finalPrice == null || finalPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new RentalValidationException(
                    "Final price cannot be null or negative."
            );
        }
        this.finalPrice = finalPrice;
    }

    /**
     * Returns a string representation of the rental.
     *
     * @return a string containing the rental details
     */
    @Override
    public String toString() {
        return "Rental{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", car=" + car +
                ", dateTheRentalBegins=" + dateTheRentalBegins +
                ", dateExpectedBack=" + dateExpectedBack +
                ", dateActuallyReturned=" + dateActuallyReturned +
                ", finalPrice=" + finalPrice +
                ", rentalStatuses=" + rentalStatuses +
                '}';
    }
}
