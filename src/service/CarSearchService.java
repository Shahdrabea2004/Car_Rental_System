package service;

import model.Car;
import model.CarSearchCriteria;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides search operations for cars in the rental system.
 *
 * Supports searching by car type, brand, status, and daily rental price.
 * Multiple search criteria can be combined in a single search.
 */
public class CarSearchService {

    /**
     * Stores the cars that can be searched.
     */
    private final List<Car> cars;


    /**
     * Creates a CarSearchService using the given list of cars.
     *
     * @param cars list of cars to search
     */
    public CarSearchService(List<Car> cars) {
        this.cars = cars;
    }

    /**
     * Searches for cars that match the given criteria.
     *
     * A criterion is ignored when its value is null.
     * This allows searching using one criterion or combining
     * multiple criteria together.
     *
     * @param carSearchCriteria criteria used to filter cars
     * @return list of cars matching all specified criteria
     */
    public List<Car> search(CarSearchCriteria carSearchCriteria) {

        List<Car> result = new ArrayList<>();

        for (Car car : cars) {
            // Search by type
             if((carSearchCriteria.getType() != null) &&
                !(car.getClass().getSimpleName().equalsIgnoreCase(carSearchCriteria.getType()))){
                 continue;
             }

            // Search by brand
             if((carSearchCriteria.getBrand()!=null) &&
                 !(car.getBrand().equalsIgnoreCase(carSearchCriteria.getBrand()))){
                 continue;
             }

            // Search by status
             if((carSearchCriteria.getStatus()!=null) &&
                     (car.getCarStatus() != carSearchCriteria.getStatus())){
                 continue;
             }

            // Search by maximum price
            if((carSearchCriteria.getMaxPrice()!=null) &&
                    (car.getBaseDailyRentalPrice().compareTo(carSearchCriteria.getMaxPrice())>0)){
                continue;
            }

            // Search by minimum price
            if((carSearchCriteria.getMinPrice()!=null) &&
                    (car.getBaseDailyRentalPrice().compareTo(carSearchCriteria.getMinPrice())<0)){
                continue;
            }

            result.add(car);
        }

        return result;
    }




}
