package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents an economy car in the rental system.
 * Economy cars are fuel-efficient and budget-friendly vehicles
 * that may have a small discount applied to their rental price.
 */
public class EconomyCar extends Car {

    // Fuel efficiency measured in kilometers per liter.
    private int fuelEfficiency;
    // Optional discount percentage, defaulting to 0%.
    private double discount = 0;


    /**
     * Constructs an EconomyCar with its common car information
     * and fuel efficiency.
     *
     * @param id unique identifier of the car
     * @param brand brand of the car
     * @param model model of the car
     * @param manufacturingYear manufacturing year of the car
     * @param description description of the car
     * @param baseDailyRentalPrice base daily rental price
     * @param fuelEfficiency fuel efficiency in kilometers per liter
     */
    public EconomyCar(String id, String brand, String model, String manufacturingYear, String description, BigDecimal baseDailyRentalPrice, int fuelEfficiency) {
        super(id, brand, model, manufacturingYear, baseDailyRentalPrice);
        validateFuelEfficiency(fuelEfficiency);
        this.fuelEfficiency = fuelEfficiency;
    }


    /**
     * Validates the fuel efficiency of the car.
     *
     * @param fuelEfficiency fuel efficiency to validate
     * @throws IllegalArgumentException if fuel efficiency is less than or equal to zero
     */
    private void validateFuelEfficiency(int fuelEfficiency) {
        if (fuelEfficiency <= 0) {
            throw new IllegalArgumentException(
                    "Fuel efficiency must be greater than 0."
            );
        }
    }

    /**
     * Updates the fuel efficiency of the car.
     *
     * @param fuelEfficiency new fuel efficiency in kilometers per liter
     * @throws IllegalArgumentException if fuel efficiency is less than or equal to zero
     */
    public void setFuelEfficiency(int fuelEfficiency) {
        validateFuelEfficiency(fuelEfficiency);
        this.fuelEfficiency = fuelEfficiency;
    }

    /** * Returns the fuel efficiency of the car. * * @return fuel efficiency in kilometers per liter */
    public int getFuelEfficiency() {
        return fuelEfficiency;
    }

    /**
     * Sets the discount percentage applied to the rental price.
     *
     * @param discount discount percentage between 0 and 100
     * @throws IllegalArgumentException if the discount is outside the range 0-100
     */
    public void setDiscount(double discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException(
                    "Discount must be between 0 and 100."
            );
        }
        this.discount = discount;
    }

    /**
     * Returns the current discount percentage.
     *
     * @return discount percentage
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * Calculates the rental price for the specified number of days.
     * The discount is calculated as a percentage of the total base price
     * and then subtracted from that price.
     *
     * @param days number of rental days
     * @return rental price after applying the discount
     */
    @Override
    public BigDecimal calculatePrice(int days) {
       validateRentalDays(days);

        BigDecimal total = getBaseDailyRentalPrice().multiply(BigDecimal.valueOf(days));
        BigDecimal discountAmount = total.multiply(BigDecimal.valueOf(getDiscount()))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        return total.subtract(discountAmount);
    }

    /**
     * Returns a string representation of the economy car,
     * including its common car information, fuel efficiency,
     * and discount.
     *
     * @return string representation of the economy car
     */
    @Override
    public String toString() {
        return "EconomyCar{" + super.toString() +
                "fuelEfficiency=" + getFuelEfficiency() +
                ", discount=" + getDiscount() +
                '}';
    }
}
