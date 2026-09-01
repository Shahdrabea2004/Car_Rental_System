package model;

import exception.CarStatusException;

import java.math.BigDecimal;

/**
 * Abstract base class representing a car in the rental system.
 * Contains common car information and controls valid car status transitions.
 */
public abstract class Car {

    // Immutable car information
    private final int id;
    private final String brand;
    private final String model;
    private final String manufacturingYear;

    // Mutable car information
    private BigDecimal baseDailyRentalPrice;

    // Current state of the car
    private CarStatus carStatus;


    /**
     * Constructs a Car with the given basic information.
     * A newly created car is initially AVAILABLE.
     */
    public Car(int id, String brand, String model, String manufacturingYear, BigDecimal baseDailyRentalPrice) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.manufacturingYear = manufacturingYear;
        this.baseDailyRentalPrice = baseDailyRentalPrice;
        this.carStatus = CarStatus.AVAILABLE;
    }

    /**
     * Calculates the rental price for the specified number of days.
     * Each concrete car type provides its own implementation.
     *
     * @param days number of rental days
     * @return calculated rental price
     */
    public abstract BigDecimal calculatePrice(int days);

    /**
     * Validates the number of rental days.
     *
     * @param days number of rental days
     * @throws IllegalArgumentException if days is less than one
     */
    protected void validateRentalDays(int days) {
        if (days < 1) {
            throw new IllegalArgumentException(
                    "Rental days must be at least 1."
            );
        }
    }


    // Getters for immutable car information
    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturingYear() {
        return manufacturingYear;
    }

    // Getters and setters for mutable car information
    public void setBaseDailyRentalPrice(BigDecimal baseDailyRentalPrice) {
        this.baseDailyRentalPrice = baseDailyRentalPrice;
    }

    public BigDecimal getBaseDailyRentalPrice() {
        return baseDailyRentalPrice;
    }


    /**
     * Validates that the car is in the expected status
     * before performing a status transition.
     *
     * @param expectedStatus required current status
     * @param errorMessage   error message for an invalid transition
     * @throws CarStatusException if the current status is invalid
     */
    private void validateStatus(CarStatus expectedStatus, String errorMessage) throws CarStatusException {
        if (this.carStatus != expectedStatus) {
            throw new CarStatusException(errorMessage);
        }
    }

    /**
     * Changes the car status from AVAILABLE to RENTED.
     *
     * @throws CarStatusException if the car is not available
     */
    public void rent() throws CarStatusException {
        validateStatus(CarStatus.AVAILABLE, "Car must be available to be rented.");
        this.carStatus = CarStatus.RENTED;

    }

    /**
     * Changes the car status from RENTED to AVAILABLE.
     *
     * @throws CarStatusException if the car is not rented
     */
    public void returnCar() throws CarStatusException {
        validateStatus(CarStatus.RENTED, "Only a rented car can be returned.");
        this.carStatus = CarStatus.AVAILABLE;

    }

    /**
     * Changes the car status from AVAILABLE to UNDER_MAINTENANCE.
     *
     * @throws CarStatusException if the car is not available
     */
    public void sendToMaintenance() throws CarStatusException {
        validateStatus(CarStatus.AVAILABLE, "Only an available car can be placed into maintenance.");
        this.carStatus = CarStatus.UNDER_MAINTENANCE;
    }

    /**
     * Changes the car status from UNDER_MAINTENANCE to AVAILABLE.
     *
     * @throws CarStatusException if the car is not under maintenance
     */
    public void returnFromMaintenance() throws CarStatusException {
        validateStatus(CarStatus.UNDER_MAINTENANCE, "Car is not under maintenance.");
        this.carStatus = CarStatus.AVAILABLE;
    }

    /**
     * Returns the current status of the car.
     *
     * @return current car status
     */
    public CarStatus getCarStatus() {
        return carStatus;
    }

    /**
     * Returns a string representation of the car.
     *
     * @return car information as a string
     */
    @Override
    public String toString() {
        return "Car{" + "id=" + getId() +
                ", brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", manufacturingYear='" + getManufacturingYear() + '\'' +
                ", baseDailyRentalPrice=" + getBaseDailyRentalPrice() +
                ", carStatus=" + getCarStatus() + '}';
    }
}
