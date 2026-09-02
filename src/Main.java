import model.*;
import service.EmployeeService;
import service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Car> cars = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();

    static List<Rental> rentals = new ArrayList<>();

    static EmployeeService employeeService =
            new EmployeeServiceImpl(cars, rentals);
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
       List<Car>cars = employeeService.viewAllCars();

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

    static void registerCustomer(){

    }

    static void viewAvailableCars(){

    }

    static void searchCars(){

    }

    static void rentCar(){

    }

    static void returnCar(){

    }

    static void viewRentalHistory(){

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
                        viewAvailableCars();
                        break;

                    case 3:
                        searchCars();
                        break;

                    case 4:
                        rentCar();
                        break;

                    case 5:
                        returnCar();
                        break;

                    case 6:
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