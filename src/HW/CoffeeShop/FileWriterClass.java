package HW.CoffeeShop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWriterClass {
    public static void write() {
        Scanner sc = new Scanner(System.in);
        //i had to write the whole path because it wouldn't append into the file otherwise
        File file = new File("C:\\Users\\USER\\IdeaProjects\\OOP\\src\\HW\\CoffeeShop\\orders.csv");
        char ans = 'Y';
        try {
            while (ans == 'Y') {
                FileWriter writer = new FileWriter(file, true);
                System.out.println("Enter the name of the customer: ");
                String customer_name = sc.nextLine();
                System.out.println("Enter the name of the order: ");
                String order_name = sc.nextLine();
                System.out.println("Enter the price of the order: ");
                double price = sc.nextDouble();
                sc.nextLine();
                System.out.println("Enter the date and time of the order(YYYY-MM-DD): ");
                String date = sc.nextLine();

                writer.write(customer_name + "," + order_name + "," + price + "," + date + "\n");

                writer.close();

                System.out.println("CSV file written successfully.");
                sc.nextLine();
                System.out.println("Do you want to continue? (Y/N)");
                ans = sc.next().charAt(0);

            } } catch(IOException ioe){
                System.err.println(ioe.getMessage());
            }

    }
}
