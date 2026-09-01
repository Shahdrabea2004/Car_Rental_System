package service;

import model.Rental;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public class RentalService {

    private final static BigDecimal LATE_RETURN_FEE = BigDecimal.valueOf(50);


    /**
     * Calculates the final rental price.
     *
     * The base price is calculated using the number of days
     * between the rental start date and the expected return date.
     *
     * If the car is returned late, a fixed late fee is added
     * for each late day.
     */
    public void calculateFinalPrice(Rental rental) {
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

        rental.setFinalPrice(total);

    }
}
