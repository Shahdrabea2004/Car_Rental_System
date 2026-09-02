package service;

import model.Car;
import model.Rental;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the operations that an employee can perform
 * to manage the agency fleet and rental records.
 */
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * Stores all cars in the agency fleet.
     */
    private final List<Car> cars;

    /**
     * Stores all rental records in the system.
     */
    private final List<Rental> rentals;

    /**
     * Creates an EmployeeServiceImpl with the given car and rental lists.
     *
     * @param cars list containing all cars in the fleet
     * @param rentals list containing all rental records in the system
     */
    public EmployeeServiceImpl(List<Car> cars, List<Rental> rentals) {
        this.cars = cars;
        this.rentals = rentals;
    }

    /**
     * Adds a new car to the fleet.
     *
     * @param car car to be added
     */
    @Override
    public void addCar(Car car) {
        for (Car existingCar : cars) {
            if (existingCar.getId().equals(car.getId())) {
                throw new IllegalArgumentException(
                        "Car ID already exists."
                );
            }
        }
        cars.add(car);
    }

    /**
     * Returns all cars in the fleet with their current statuses.
     *
     * @return a copy of the list containing all cars
     */
    @Override
    public List<Car> viewAllCars() {
        return new ArrayList<>(cars);
    }


    /**
     * Returns all rentals in the system.
     *
     * @return a copy of the list containing all rentals
     */
    @Override
    public List<Rental> viewAllRentals() {
        return new ArrayList<>(rentals);
    }

    /**
     * Puts an available car under maintenance.
     *
     * @param car car to be placed under maintenance
     */
    @Override
    public void putCarUnderMaintenance(Car car) {
          car.sendToMaintenance();
    }

    /**
     * Returns a car from maintenance to the available fleet.
     *
     * @param car car to be returned from maintenance
     */
    @Override
    public void returnCarFromMaintenance(Car car) {
        car.returnFromMaintenance();
    }
}
