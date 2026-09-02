import exception.*;
import model.*;
import service.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Shared system data
    static List<Car> cars = new ArrayList<>();
    static List<Rental> rentals = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();

    static Scanner input = new Scanner(System.in);
    // Service objects
    static EmployeeService employeeService =
            new EmployeeServiceImpl(cars, rentals);
    static CustomerService customerService = new CustomerService(customers);
    static CarSearchService carSearchService = new CarSearchService(cars);
    static RentalService rentalService = new RentalService(rentals);

    // Add a new car
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

    // Display all cars
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

    // Display all rentals
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

    // Put a car under maintenance
    static void putCarUnderMaintenance() {

        try {
            System.out.print("Enter car ID: ");
            String id = input.next();

            Car car = null;

            for (Car c : cars) {
                if (c.getId().equals(id)) {
                    car = c;
                    break;
                }
            }

            if (car == null) {
                System.out.println("Car not found.");
                return;
            }

            employeeService.putCarUnderMaintenance(car);

            System.out.println("Car placed under maintenance successfully.");

        } catch (CarStatusException e) {
            System.out.println(e.getMessage());
        }
    }

    // Return a car from maintenance
    static void returnCarFromMaintenance() {

        try {
            System.out.print("Enter car ID: ");
            String id = input.next();

            Car car = null;

            for (Car c : cars) {
                if (c.getId().equals(id)) {
                    car = c;
                    break;
                }
            }

            if (car == null) {
                System.out.println("Car not found.");
                return;
            }

            employeeService.returnCarFromMaintenance(car);

            System.out.println("Car returned from maintenance successfully.");

        } catch (CarStatusException e) {
            System.out.println(e.getMessage());
        }
    }

    // Handle employee menu
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

    // Register a new customer
    static void registerCustomer() {

        try {
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

        } catch (CustomerValidationException e) {
            System.out.println(e.getMessage());
        } catch (CustomerServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    // Find a registered customer
    static Customer getRegisteredCustomer() {

        System.out.print("Enter customer ID: ");
        String customerId = input.next();

        Customer customer = customerService.searchCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer is not registered.");
        }

        return customer;
    }

    // Display available cars
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

    // Search cars using different criteria
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

    // Rent a car
    static void rentCar(Customer customer) {

        try {
            input.nextLine();

            System.out.print("Enter rental ID: ");
            String rentalId = input.nextLine();

            System.out.print("Enter car ID: ");
            String carId = input.nextLine();

            Car car = null;

            for (Car c : cars) {
                if (c.getId().equals(carId)) {
                    car = c;
                    break;
                }
            }

            if (car == null) {
                System.out.println("Car not found.");
                return;
            }

            System.out.print("Enter rental start date (YYYY-MM-DD): ");
            LocalDate startDate = LocalDate.parse(input.nextLine());

            System.out.print("Enter expected return date (YYYY-MM-DD): ");
            LocalDate expectedReturnDate = LocalDate.parse(input.nextLine());

            Rental rental = new Rental(
                    rentalId,
                    customer,
                    car,
                    startDate,
                    expectedReturnDate
            );

            rentalService.rentalCar(rental);

            System.out.println("Car rented successfully.");

        } catch (DateTimeParseException e) {
            System.out.println(
                    "Invalid date format. Please use YYYY-MM-DD."
            );

        } catch (RentalValidationException e) {
            System.out.println(e.getMessage());

        } catch (RentalServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    // Return a rented car
    static void returnCar(Customer customer) {

        try {
            input.nextLine();

            System.out.print("Enter rental ID: ");
            String rentalId = input.nextLine();

            Rental rental = null;

            for (Rental r : customer.getHistoryRentals()) {
                if (r.getId().equals(rentalId)) {
                    rental = r;
                    break;
                }
            }

            if (rental == null) {
                System.out.println("Rental not found.");
                return;
            }

            System.out.print("Enter actual return date (YYYY-MM-DD): ");
            LocalDate actualReturnDate = LocalDate.parse(input.nextLine());

            rentalService.returnCar(actualReturnDate, rental);

            System.out.println("Car returned successfully.");

        } catch (DateTimeParseException e) {
            System.out.println(
                    "Invalid date format. Please use YYYY-MM-DD."
            );

        } catch (RentalValidationException e) {
            System.out.println(e.getMessage());

        } catch (RentalServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    // Display customer's rental history
    static void viewRentalHistory(Customer customer) {

        List<Rental> rentals = customer.getHistoryRentals();

        if (rentals.isEmpty()) {
            System.out.println("No rental history found.");
            return;
        }

        System.out.println("\n========================");
        System.out.println("      RENTAL HISTORY");
        System.out.println("========================");

        for (Rental rental : rentals) {
            System.out.println("Rental ID: " + rental.getId());
            System.out.println("Car: "
                    + rental.getCar().getBrand() + " "
                    + rental.getCar().getModel());
            System.out.println("Start Date: "
                    + rental.getDateTheRentalBegins());
            System.out.println("Expected Return: "
                    + rental.getDateExpectedBack());
            System.out.println("Actual Return: "
                    + rental.getDateActuallyReturned());
            System.out.println("Status: "
                    + rental.getRentalStatuses());
            System.out.println("Final Price: "
                    + rental.getFinalPrice());
            System.out.println("------------------------");
        }
    }

    // Handle customer menu
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
                        Customer customer = getRegisteredCustomer();

                        if (customer == null) {
                            break;
                        }

                        viewAvailableCars();
                        break;

                    case 3:
                        customer = getRegisteredCustomer();

                        if (customer == null) {
                            break;
                        }

                        searchCars();
                        break;

                    case 4:
                        customer = getRegisteredCustomer();

                        if (customer == null) {
                            break;
                        }

                        rentCar(customer);
                        break;

                    case 5:
                        customer = getRegisteredCustomer();

                        if (customer == null) {
                            break;
                        }

                        returnCar(customer);
                        break;

                    case 6:
                        customer = getRegisteredCustomer();

                        if (customer == null) {
                            break;
                        }

                        viewRentalHistory(customer);
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

    // Start the application
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