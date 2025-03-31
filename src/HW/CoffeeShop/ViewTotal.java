package HW.CoffeeShop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ViewTotal {
    public static void total() {
        File file = new File("C:\\Users\\USER\\IdeaProjects\\OOP\\src\\HW\\CoffeeShop\\orders.csv");
        try {
            Scanner scanner = new Scanner(file);
            Scanner inputScanner = new Scanner(System.in);

            System.out.println("Enter customer name: ");
            String name = inputScanner.nextLine();

            double sum = 0.0;

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts[0].equalsIgnoreCase(name)) {
                    sum += Double.parseDouble(parts[2]);
                }
            }

            if (sum > 0) {
                System.out.println(name + " paid a total of: " + sum);
            } else {
                System.out.println("No orders found for " + name);
            }
        } catch (FileNotFoundException fe) {
            System.err.println("Error: " + fe.getMessage());
        }
    }
}
