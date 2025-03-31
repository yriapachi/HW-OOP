package HW.CoffeeShop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderClass {
    public static void read() {
        File file = new File("C:\\Users\\USER\\IdeaProjects\\OOP\\src\\HW\\CoffeeShop\\orders.csv");
        try (Scanner scanner = new Scanner(file)) {
            char ans = 'Y';

            while (ans == 'Y') {
                System.out.println("Enter the date (YYYY-MM-DD): ");
                String date = scanner.nextLine();

                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }

                boolean found = false;

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");

                    if (parts.length > 3 && parts[3].equals(date)) {
                        found = true;
                        System.out.println("Order placed: " + parts[1] + " at " + parts[3]);
                    }
                }

                if (!found) {
                    System.out.println("No orders found for the date: " + date);
                }

                System.out.println("Do you want to continue? (Y/N): ");
                ans = scanner.next().charAt(0);
                scanner.nextLine();
            }

        } catch (FileNotFoundException fe) {
            System.out.println("Error: " + fe.getMessage());
        }
    }
}