package service;

import model.Car;
import model.Rental;

import java.util.List;

/**
 * Defines the operations that an employee can perform
 * to manage the agency fleet and rental records.
 */
public interface EmployeeService {

    /**
     * Adds a new car to the fleet.
     *
     * @param car car to be added
     */
    void addCar(Car car);

    /**
     * Returns all cars in the fleet with their current statuses.
     *
     * @return list of all cars
     */
    List<Car> viewAllCars();


    /**
     * Returns all rentals in the system.
     *
     * @return list of all rentals
     */
    List<Rental> viewAllRentals();


    /**
     * Puts an available car under maintenance.
     *
     * @param car car to be placed under maintenance
     */
    void putCarUnderMaintenance(Car car);

    /**
     * Returns a car from maintenance to the available fleet.
     *
     * @param car car to be returned from maintenance
     */
    void returnCarFromMaintenance(Car car);

}
