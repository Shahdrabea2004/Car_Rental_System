package model;

import java.math.BigDecimal;

/**
 * Represents an SUV car in the rental system.
 *
 * SUVs are large, multi-purpose vehicles suitable for families
 * or off-road use. The rental price can include a surcharge
 * and an additional adjustment based on the number of seats.
 */
public class SUV extends Car {

    // Number of seats available in the SUV.
    private int numberOfSeats;
    // Indicates whether the SUV has four-wheel drive.
    private boolean hasFourWheelDrive = false;

    // Additional fixed surcharge added to the rental price.
    private BigDecimal surcharge = BigDecimal.ZERO;


    /**
     * Constructs an SUV with its common car information
     * and number of seats.
     *
     * @param id                   unique identifier of the car
     * @param brand                brand of the car
     * @param model                model of the car
     * @param manufacturingYear    manufacturing year of the car
     * @param baseDailyRentalPrice base daily rental price
     * @param numberOfSeats        number of seats in the SUV
     */
    public SUV(int id, String brand, String model, String manufacturingYear, BigDecimal baseDailyRentalPrice, int numberOfSeats) {
        super(id, brand, model, manufacturingYear, baseDailyRentalPrice);
        validateNumberOfSeated(numberOfSeats);
        this.numberOfSeats = numberOfSeats;
    }

    /**
     * Validates the number of seats in the SUV.
     *
     * @param numberOfSeats number of seats to validate
     * @throws IllegalArgumentException if the number of seats is less than 4
     */
    private void validateNumberOfSeated(int numberOfSeats) {
        if (numberOfSeats < 4) {
            throw new IllegalArgumentException(
                    "Number of seats must be at least 4."
            );
        }

    }

    /**
     * Returns the number of seats in the SUV.
     *
     * @return number of seats
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * Updates the number of seats in the SUV.
     *
     * @param numberOfSeats new number of seats
     * @throws IllegalArgumentException if the number of seats
     *                                  is must be at least 4
     */
    public void setNumberOfSeats(int numberOfSeats) {
        validateNumberOfSeated(numberOfSeats);
        this.numberOfSeats = numberOfSeats;
    }

    /**
     * Returns whether the SUV has four-wheel drive.
     *
     * @return true if four-wheel drive is available,
     *         otherwise false
     */
    public boolean isHasFourWheelDrive() {
        return hasFourWheelDrive;
    }

    /**
     * Sets whether the SUV has four-wheel drive.
     *
     * @param hasFourWheelDrive true if the SUV has four-wheel drive
     */
    public void setHasFourWheelDrive(boolean hasFourWheelDrive) {
        this.hasFourWheelDrive = hasFourWheelDrive;
    }


    /**
     * Returns the surcharge applied to the rental price.
     *
     * @return surcharge amount
     */
    public BigDecimal getSurcharge() {
        return surcharge;
    }


    /**
     * Sets the surcharge amount.
     *
     * @param surcharge surcharge amount
     * @throws IllegalArgumentException if surcharge is null or negative
     */
    public void setSurcharge(BigDecimal surcharge) {
        if (surcharge == null || surcharge.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Surcharge must be zero or greater.");
        }
        this.surcharge = surcharge;
    }

    /**
     * Calculates the rental price for the specified number of days.
     *
     * The price consists of:
     * - The base rental price multiplied by the number of days.
     * - The SUV's fixed surcharge.
     * - An additional price adjustment based on the number of seats.
     *
     * @param days number of rental days
     * @return calculated rental price
     */
    @Override
    public BigDecimal calculatePrice(int days) {
        validateRentalDays(days);

        BigDecimal total = getBaseDailyRentalPrice().multiply(BigDecimal.valueOf(days));

        BigDecimal seatsPrice = BigDecimal.ZERO;
        if (getNumberOfSeats() <= 4) {
            seatsPrice = BigDecimal.valueOf(400);
        } else if (getNumberOfSeats() > 4 && getNumberOfSeats() <= 7) {
            seatsPrice = BigDecimal.valueOf(700);
        } else if (getNumberOfSeats() > 7) {
            seatsPrice = BigDecimal.valueOf(1000);
        }

        return total
                .add(getSurcharge())
                .add(seatsPrice);

    }

    /**
     * Returns a string representation of the SUV.
     *
     * @return string containing the SUV's information
     */
    @Override
    public String toString() {
        return "SUV{" + super.toString() +
                "numberOfSeats=" + numberOfSeats +
                ", hasFourWheelDrive=" + hasFourWheelDrive +
                ", surcharge=" + surcharge +
                '}';
    }
}
