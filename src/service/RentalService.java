package service;

import exception.RentalServiceException;
import model.CarStatus;
import model.Customer;
import model.Rental;
import model.RentalStatuses;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

/**
 * Provides business operations for managing rental transactions.
 *
 * This service handles renting and returning cars, validating rental
 * conditions, tracking customers with active rentals, and calculating
 * final rental prices including late return fees.
 */
public class RentalService {

    /**
     * Fixed fee charged for each day a car is returned late.
     */
    private static final BigDecimal LATE_RETURN_FEE = BigDecimal.valueOf(50);


    /**
     * Stores customers who currently have an active rental.
     */
    private final Set<Customer> customersWithActiveRental = new HashSet<>();

    /**
     * Calculates the final rental price.
     * <p>
     * The base price is calculated using the number of planned
     * rental days. If the car is returned late, a fixed late
     * fee is added for each late day.
     *
     * @param rental rental whose final price will be calculated
     * @return calculated final rental price
     */
    public BigDecimal calculateFinalPrice(Rental rental) {
        // Calculate the number of planned rental days.
        int rentalDays = (int) ChronoUnit.DAYS.between(
                rental.getDateTheRentalBegins(),
                rental.getDateExpectedBack());

        if (rentalDays < 1) {
            rentalDays = 1;
        }
        // Calculate the base rental price according to the car type.
        BigDecimal total = rental.getCar().calculatePrice(rentalDays);

        // Check whether the car was returned after the expected date.
        if (rental.getDateActuallyReturned() != null && rental.getDateActuallyReturned().isAfter(rental.getDateExpectedBack())) {
            // Calculate only the number of late days.
            int lateDays = (int) ChronoUnit.DAYS.between(
                    rental.getDateExpectedBack(),
                    rental.getDateActuallyReturned()
            );

            // Calculate the total late return fee.
            BigDecimal lateFee = BigDecimal.valueOf(lateDays).multiply(LATE_RETURN_FEE);

            total = total.add(lateFee);

        }

        return total;
    }

    /**
     * Validates that the car is available for rental.
     *
     * @param rental rental containing the car to be checked
     * @throws RentalServiceException if the car is not available
     */
    public void validateCarAvailable(Rental rental) {
        if (rental.getCar().getCarStatus() != CarStatus.AVAILABLE) {
            throw new RentalServiceException(
                    "Car is not available."
            );
        }

    }

    /**
     * Checks whether the customer already has an active rental.
     *
     * @param rental rental containing the customer to be checked
     * @throws RentalServiceException if the customer already has
     *                                an active rental
     */
    public void checkCustomerHasNoActiveRental(Rental rental) {
        if (customersWithActiveRental.contains(rental.getCustomer())) {
            throw new RentalServiceException(
                    "Customer already has an active rental."
            );
        }

    }

    /**
     * Processes a new car rental.
     *
     * @param rental rental to be processed
     * @throws RentalServiceException if the customer already has
     *                                an active rental or the car
     *                                is unavailable
     */
    public void rentalCar(Rental rental) {
        checkCustomerHasNoActiveRental(rental);
        validateCarAvailable(rental);

        rental.setRentalStatuses(RentalStatuses.ACTIVE);
        rental.getCar().rent();
        rental.getCustomer().addRental(rental);

        customersWithActiveRental.add(rental.getCustomer());
    }

    /**
     * Checks whether the rental has already been completed.
     *
     * @param rental rental to be checked
     * @throws RentalServiceException if the rental is already completed
     */
    public void checkIsAlreadyCompleted(Rental rental) {
        if (rental.getRentalStatuses() == RentalStatuses.COMPLETED) {
            throw new RentalServiceException(
                    "Rental is already completed."
            );
        }

    }

    /**
     * Processes the return of a rented car.
     *
     * The method sets the actual return date, calculates the final
     * rental price, completes the rental, returns the car to the
     * available state, and removes the customer from active rentals.
     *
     * @param dateActuallyReturned actual date the car was returned
     * @param rental rental being completed
     * @throws RentalServiceException if the rental is already completed
     */
    public void returnCar(LocalDate dateActuallyReturned, Rental rental) {

        checkIsAlreadyCompleted(rental);

        rental.setDateActuallyReturned(dateActuallyReturned);

        rental.setFinalPrice(calculateFinalPrice(rental));

        rental.setRentalStatuses(RentalStatuses.COMPLETED);

        rental.getCar().returnCar();

        customersWithActiveRental.remove(rental.getCustomer());
    }


}
