import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void employeeFeature() {

    }

    public static void customerFeature() {

    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
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