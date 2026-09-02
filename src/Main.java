import model.*;
import service.CarSearchService;
import service.CustomerService;
import service.EmployeeService;
import service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Car> cars = new ArrayList<>();
    static List<Rental> rentals = new ArrayList<>();

    static List<Customer> customers = new ArrayList<>();
    static EmployeeService employeeService =
            new EmployeeServiceImpl(cars, rentals);

    static CustomerService customerService = new CustomerService(customers);

    static CarSearchService carSearchService = new CarSearchService(cars);
    static Scanner input = new Scanner(System.in);


    static void addCar() {

        input.nextLine();

        System.out.print("Enter car ID: ");
        String id = input.nextLine();

        System.out.print("Enter car brand: ");
        String brand = input.nextLine();

        System.out.print("Enter car model: ");
        String model = input.nextLine();

        System.out.print("Enter manufacturing year: ");
        String manufacturingYear = input.nextLine();

        System.out.print("Enter base daily rental price: ");
        BigDecimal baseDailyRentalPrice = input.nextBigDecimal();
        input.nextLine();

        System.out.println("\nChoose car type:");
        System.out.println("1. Economy");
        System.out.println("2. Luxury");
        System.out.println("3. SUV");
        System.out.println("4. Truck");
        System.out.print("Enter your choice: ");

        int type = input.nextInt();

        Car car = null;

        switch (type) {

            case 1:
                System.out.print("Enter fuel efficiency: ");
                int fuelEfficiency = input.nextInt();

                car = new EconomyCar(
                        id,
                        brand,
                        model,
                        manufacturingYear,
                        baseDailyRentalPrice,
                        fuelEfficiency
                );
                break;

            case 2:
                System.out.print("Enter number of premium features: ");
                int numberOfPremiumFeatures = input.nextInt();
                input.nextLine();

                List<String> premiumFeatures = new ArrayList<>();

                for (int i = 0; i < numberOfPremiumFeatures; i++) {
                    System.out.print("Enter premium feature " + (i + 1) + ": ");
                    String premiumFeature = input.nextLine();
                    premiumFeatures.add(premiumFeature);
                }

                car = new LuxuryCar(
                        id,
                        brand,
                        model,
                        manufacturingYear,
                        baseDailyRentalPrice,
                        premiumFeatures
                );
                break;

            case 3:
                System.out.print("Enter number of seats: ");
                int seats = input.nextInt();

                car = new SUV(
                        id,
                        brand,
                        model,
                        manufacturingYear,
                        baseDailyRentalPrice,
                        seats
                );
                break;

            case 4:
                System.out.print("Enter cargo capacity: ");
                double cargoCapacity = input.nextDouble();

                System.out.print("Does it require a special license? (true/false): ");
                boolean specialLicense = input.nextBoolean();

                car = new Truck(
                        id,
                        brand,
                        model,
                        manufacturingYear,
                        baseDailyRentalPrice,
                        cargoCapacity,
                        specialLicense
                );
                break;

            default:
                System.out.println("Invalid car type.");
                return;
        }

        employeeService.addCar(car);
        System.out.println("Car added successfully.");
    }

    static void viewAllCars() {
        List<Car> cars = employeeService.viewAllCars();

        if (cars.isEmpty()) {
            System.out.println("No cars available.");
            return;
        }

        for (Car car : cars) {
            System.out.println(car);
        }
    }

    static void viewAllRentals() {
        List<Rental> rentals = employeeService.viewAllRentals();

        if (rentals.isEmpty()) {
            System.out.println("No rentals available.");
            return;
        }

        for (Rental rental : rentals) {
            System.out.println(rental);
        }
    }

    static void putCarUnderMaintenance() {
        System.out.print("Enter car ID: ");
        String id = input.next();

        boolean foundCar = false;

        for (Car car : cars) {
            if (car.getId().equals(id)) {
                employeeService.putCarUnderMaintenance(car);
                System.out.println("Car placed under maintenance successfully.");
                foundCar = true;
                break;
            }
        }

        if (!foundCar) {
            System.out.println("This car was not found.");
        }
    }

    static void returnCarFromMaintenance() {
        System.out.print("Enter car ID: ");
        String id = input.next();

        boolean foundCar = false;

        for (Car car : cars) {
            if (car.getId().equals(id)) {
                employeeService.returnCarFromMaintenance(car);
                System.out.println("Car returned from maintenance successfully.");
                foundCar = true;
                break;
            }
        }

        if (!foundCar) {
            System.out.println("This car was not found.");
        }
    }

    static void employeeFeature() {

        int option = 0;

        while (option != 6) {

            System.out.println("\n========================");
            System.out.println("     EMPLOYEE MENU");
            System.out.println("========================");
            System.out.println("1. Add Car");
            System.out.println("2. View All Cars");
            System.out.println("3. View All Rentals");
            System.out.println("4. Put Car Under Maintenance");
            System.out.println("5. Return Car From Maintenance");
            System.out.println("6. Back");
            System.out.print("Enter your choice: ");

            try {
                option = input.nextInt();

                switch (option) {
                    case 1:
                        // Add Car
                        addCar();
                        break;

                    case 2:
                        // View All Cars
                        viewAllCars();
                        break;

                    case 3:
                        // View All Rentals
                        viewAllRentals();
                        break;

                    case 4:
                        // Put Car Under Maintenance
                        putCarUnderMaintenance();
                        break;

                    case 5:
                        // Return Car From Maintenance
                        returnCarFromMaintenance();
                        break;

                    case 6:
                        System.out.println("Returning to main menu...");
                        break;

                    default:
                        System.out.println("Invalid option. Please choose 1-6.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
            }
        }
    }

    static void registerCustomer() {

        input.nextLine();

        System.out.print("Enter customer ID: ");
        String id = input.nextLine();

        System.out.print("Enter full name: ");
        String fullName = input.nextLine();

        System.out.print("Enter email: ");
        String email = input.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = input.nextLine();

        List<Rental> historyRentals = new ArrayList<>();

        Customer customer = new Customer(
                id,
                fullName,
                email,
                phoneNumber,
                historyRentals
        );

        customerService.registerCustomer(customer);

        System.out.println("Customer registered successfully.");
    }

    static Customer getRegisteredCustomer() {

        System.out.print("Enter customer ID: ");
        String customerId = input.next();

        Customer customer = customerService.searchCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer is not registered.");
        }

        return customer;
    }
    static void viewAvailableCars() {
        CarSearchCriteria carSearchCriteria = new CarSearchCriteria();
        carSearchCriteria.setStatus(CarStatus.AVAILABLE);

        List<Car> result = carSearchService.search(carSearchCriteria);

        if (result.isEmpty()) {
            System.out.println("No cars available.");
            return;
        }

        System.out.println("\nAvailable Cars:");
        for (Car car : result) {
            System.out.println(car);
        }
    }

    static void searchCars() {
        System.out.println("\n========================");
        System.out.println("       SEARCH CARS");
        System.out.println("========================");
        System.out.println("1. Search by Type");
        System.out.println("2. Search by Brand");
        System.out.println("3. Search by Price Range");
        System.out.println("4. Search Available Cars");
        System.out.println("5. Search by Multiple Criteria");
        System.out.println("6. Back");
        System.out.print("Enter your choice: ");

        int option = input.nextInt();
        input.nextLine();

        List<Car> result;
        CarSearchCriteria carSearchCriteria = new CarSearchCriteria();

        switch (option) {

            case 1:
                // Search by Type
                System.out.print("Enter car type (EconomyCar, LuxuryCar, SUV, Truck): ");
                String type = input.nextLine();

                carSearchCriteria.setType(type);
                break;

            case 2:
                // Search by Brand
                System.out.print("Enter car brand: ");
                String brand = input.nextLine();

                carSearchCriteria.setBrand(brand);
                break;

            case 3:
                // Search by Price Range
                System.out.print("Enter minimum price: ");
                BigDecimal minPrice = input.nextBigDecimal();

                System.out.print("Enter maximum price: ");
                BigDecimal maxPrice = input.nextBigDecimal();

                carSearchCriteria.setMinPrice(minPrice);
                carSearchCriteria.setMaxPrice(maxPrice);
                break;

            case 4:
                // Search Available Cars
                carSearchCriteria.setStatus(CarStatus.AVAILABLE);
                break;

            case 5:
                // Search by Multiple Criteria
                System.out.print("Enter car type (EconomyCar, LuxuryCar, SUV, Truck) or press Enter to skip: ");
                String typeInput = input.nextLine();

                if (!typeInput.isEmpty()) {
                    carSearchCriteria.setType(typeInput);
                }

                System.out.print("Enter car brand (or press Enter to skip): ");
                String brandInput = input.nextLine();

                if (!brandInput.isEmpty()) {
                    carSearchCriteria.setBrand(brandInput);
                }

                System.out.print("Enter minimum price (or press Enter to skip): ");
                String minPriceInput = input.nextLine();

                if (!minPriceInput.isEmpty()) {
                    carSearchCriteria.setMinPrice(
                            new BigDecimal(minPriceInput)
                    );
                }

                System.out.print("Enter maximum price (or press Enter to skip): ");
                String maxPriceInput = input.nextLine();

                if (!maxPriceInput.isEmpty()) {
                    carSearchCriteria.setMaxPrice(
                            new BigDecimal(maxPriceInput)
                    );
                }

                System.out.print("Search available cars only? (yes/no): ");
                String available = input.nextLine();

                if (available.equalsIgnoreCase("yes")) {
                    carSearchCriteria.setStatus(CarStatus.AVAILABLE);
                }

                if (typeInput.isEmpty()
                        && brandInput.isEmpty()
                        && minPriceInput.isEmpty()
                        && maxPriceInput.isEmpty()
                        && available.equalsIgnoreCase("no")) {

                    System.out.println("No search criteria entered.");
                    return;
                }

                break;
            case 6:
                System.out.println("Returning to customer menu...");
                return;

            default:
                System.out.println("Invalid option. Please choose 1-6.");
                return;
        }

        result = carSearchService.search(carSearchCriteria);

        if (result.isEmpty()) {
            System.out.println("No cars found.");
            return;
        }

        System.out.println("\nSearch Results:");

        for (Car car : result) {
            System.out.println(car);
        }
    }

    static void rentCar() {

    }

    static void returnCar() {

    }

    static void viewRentalHistory() {

    }

    public static void customerFeature() {

        int option = 0;

        while (option != 7) {

            System.out.println("\n========================");
            System.out.println("     CUSTOMER MENU");
            System.out.println("========================");
            System.out.println("1. Register Customer");
            System.out.println("2. View Available Cars");
            System.out.println("3. Search Cars");
            System.out.println("4. Rent Car");
            System.out.println("5. Return Car");
            System.out.println("6. View Rental History");
            System.out.println("7. Back");
            System.out.print("Enter your choice: ");

            try {
                option = input.nextInt();

                switch (option) {

                    case 1:
                        registerCustomer();
                        break;

                    case 2:
                        getRegisteredCustomer();
                        viewAvailableCars();
                        break;

                    case 3:
                        getRegisteredCustomer();
                        searchCars();
                        break;

                    case 4:
                        getRegisteredCustomer();
                        rentCar();
                        break;

                    case 5:
                        getRegisteredCustomer();
                        returnCar();
                        break;

                    case 6:
                        getRegisteredCustomer();
                        viewRentalHistory();
                        break;

                    case 7:
                        System.out.println("Returning to main menu...");
                        break;

                    default:
                        System.out.println(
                                "Invalid option. Please choose 1-7."
                        );
                }

            } catch (InputMismatchException e) {
                System.out.println(
                        "Invalid input. Please enter a number."
                );
                input.nextLine();
            }
        }
    }

    public static void main(String[] args) {

        int option = 0;

        while (option != 3) {

            System.out.println("\nChoose an option:");
            System.out.println("1. Employee");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try {
                option = input.nextInt();

                switch (option) {
                    case 1:
                        employeeFeature();
                        break;

                    case 2:
                        customerFeature();
                        break;

                    case 3:
                        System.out.println("Exiting the system...");
                        break;

                    default:
                        System.out.println("Invalid option. Please choose 1, 2, or 3.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
            }
        }

        input.close();
    }
}