package model;

import java.math.BigDecimal;


/**
 * Represents a truck in the rental system.
 *
 * Trucks are cargo and utility vehicles used for hauling goods.
 * The rental price can be adjusted based on the truck's cargo capacity.
 * Some trucks may require a special license.
 */
public class Truck extends Car {

    // Cargo capacity of the truck.
    private double cargoCapacity;

    // Indicates whether a special license is required to drive the truck.
    private boolean specialLicense;

    /**
     * Constructs a Truck with its common car information,
     * cargo capacity, and special license requirement.
     *
     * @param id                   unique identifier of the car
     * @param brand                brand of the truck
     * @param model                model of the truck
     * @param manufacturingYear    manufacturing year of the truck
     * @param baseDailyRentalPrice base daily rental price
     * @param cargoCapacity        cargo capacity of the truck
     * @param specialLicense       whether a special license is required
     */
    public Truck(String id, String brand, String model, String manufacturingYear, BigDecimal baseDailyRentalPrice, double cargoCapacity, boolean specialLicense) {
        super(id, brand, model, manufacturingYear, baseDailyRentalPrice);

        this.specialLicense = specialLicense;
        validationCargoCapacity(cargoCapacity);
        this.cargoCapacity = cargoCapacity;
    }


    /**
     * Validates the cargo capacity of the truck.
     *
     * @param cargoCapacity cargo capacity to validate
     * @throws IllegalArgumentException if cargo capacity is negative
     */
    private void validationCargoCapacity(double cargoCapacity) {
        if (cargoCapacity < 0) {
            throw new IllegalArgumentException(
                    "Cargo capacity cannot be negative."
            );
        }
    }

    /**
     * Returns the cargo capacity of the truck.
     *
     * @return cargo capacity
     */
    public double getCargoCapacity() {
        return cargoCapacity;
    }

    /**
     * Updates the cargo capacity of the truck.
     *
     * @param cargoCapacity new cargo capacity
     * @throws IllegalArgumentException if cargo capacity is negative
     */
    public void setCargoCapacity(double cargoCapacity) {
        validationCargoCapacity(cargoCapacity);
        this.cargoCapacity = cargoCapacity;
    }

    /**
     * Returns whether a special license is required.
     *
     * @return true if a special license is required,
     *         otherwise false
     */
    public boolean isSpecialLicense() {
        return specialLicense;
    }

    /**
     * Sets whether a special license is required.
     *
     * @param specialLicense true if a special license is required
     */
    public void setSpecialLicense(boolean specialLicense) {
        this.specialLicense = specialLicense;
    }

    /**
     * Calculates the rental price for the specified number of days.
     *
     * The price consists of:
     * - The base daily rental price multiplied by the number of days.
     * - An additional adjustment based on cargo capacity.
     *
     * For this implementation, each unit of cargo capacity
     * adds 50 to the rental price.
     *
     * @param days number of rental days
     * @return calculated rental price
     */
    @Override
    public BigDecimal calculatePrice(int days) {
        validateRentalDays(days);
        BigDecimal total = getBaseDailyRentalPrice().multiply(BigDecimal.valueOf(days));
        return total.add(BigDecimal.valueOf(50).multiply(BigDecimal.valueOf(getCargoCapacity())));
    }

    /**
     * Returns a string representation of the truck.
     *
     * @return string containing the truck's information
     */
    @Override
    public String toString() {
        return "Truck{" + super.toString() +
                "cargoCapacity=" + cargoCapacity +
                ", specialLicense=" + specialLicense +
                '}';
    }
}
