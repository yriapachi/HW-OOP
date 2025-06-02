package HW.CoffeeShop;

import java.sql.Connection;
import java.util.Scanner;

public class CoffeeShop {

    public static void main(String[] args) {
        System.out.println("Welcome to Central Perk!");
        Scanner sc = new Scanner(System.in);


        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            System.out.println("Error: Could not establish a connection to the database.");
            return;
        }
            System.out.println("Choose an option: ");
            System.out.println("1. Add Order");
            System.out.println("2. View Orders on a specific date");
            System.out.println("3. View total amount paid by a specific customer");
            System.out.println("0. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Database or File? (D/F)");
                    char answer1 = sc.next().charAt(0);
                    sc.nextLine();
                    switch (answer1) {
                        case 'D':
                            DBWriter.dbWrite(conn);
                            break;
                        case 'F':
                            FileWriterClass.write();
                            break;
                        default:
                            System.out.println("Invalid option, try again.");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Database or File? (D/F)");
                    char answer2 = sc.next().charAt(0);
                    sc.nextLine(); 
                    switch (answer2) {
                        case 'D':
                            DBReader.dbRead(conn);
                            break;
                        case 'F':
                            FileReaderClass.read();
                            break;
                        default:
                            System.out.println("Invalid option, try again.");
                            break;
                    }
                    break;

                case 3:
                    System.out.println("Database or File? (D/F)");
                    char answer3 = sc.next().charAt(0);
                    sc.nextLine();
                    switch (answer3) {
                        case 'D':
                            DBTotal.dbTotalPaid(conn);
                            break;
                        case 'F':
                            ViewTotal.total();
                            break;
                        default:
                            System.out.println("Invalid option, try again.");
                            break;
                    }
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
}
