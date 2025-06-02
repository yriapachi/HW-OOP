package Assignment1.b;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Eshop {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Store Inventory System ---");
            System.out.println("1. Add Product");
            System.out.println("2. Modify Product");
            System.out.println("3. Search Product");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int mainChoice = sc.nextInt();
            sc.nextLine();

            switch (mainChoice) {
                case 1:
                    handleAdd(sc);
                    break;
                case 2:
                    handleModify(sc);
                    break;
                case 3:
                    handleSearch(sc);
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }

    private static void handleAdd(Scanner sc) {
        Product product = createProductByCategory(sc);
        if (product != null) {
            product.addProduct();
        }
    }

    private static void handleModify(Scanner sc) {
        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        Product product = createProductByCatId(sc, id);
        if (product != null) {
            product.modProduct();
        }
    }

    private static void handleSearch(Scanner sc) {
        System.out.println("Select Category to Search:");
        System.out.println("1. Electronics");
        System.out.println("2. Clothing");
        System.out.println("3. Grocery");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        String tableName = null;
        switch (choice) {
            case 1:
                tableName = "electronics";
                break;
            case 2:
                tableName = "clothing";
                break;
            case 3:
                tableName = "grocery";
                break;
            default:
                System.out.println("Invalid category.");
                return;
        }

        System.out.print("Enter product name to search: ");
        String nameSearch = sc.nextLine();

        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM " + tableName + " WHERE name LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + nameSearch + "%");
            ResultSet rs = ps.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                System.out.println("ID: " + id + ", Name: " + name + ", Price: " + price);

                if (tableName.equals("electronics")) {
                    System.out.println("Brand: " + rs.getString("brandName") + ", Warranty: " + rs.getString("warrPeriod"));
                } else if (tableName.equals("clothing")) {
                    System.out.println("Size: " + rs.getString("size") + ", Material: " + rs.getString("material") + ", Color: " + rs.getString("color"));
                } else if (tableName.equals("grocery")) {
                    System.out.println("Weight: " + rs.getString("weight") + ", Expiration: " + rs.getString("expDate"));
                }
                System.out.println("-----------------------------------");
            }

            if (!found) {
                System.out.println("No product found matching that name.");
            }

        } catch (SQLException e) {
            System.out.println("Error during search: " + e.getMessage());
        }
    }

    private static Product createProductByCategory(Scanner sc) {
        System.out.println("Select Product Category:");
        System.out.println("1. Electronics");
        System.out.println("2. Clothing");
        System.out.println("3. Grocery");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        Product product = null;

        switch (choice) {
            case 1:
                System.out.print("Enter Brand Name: ");
                String brand = sc.nextLine();
                System.out.print("Enter Warranty Period: ");
                String warranty = sc.nextLine();
                product = new Electronics(id, name, price, brand, warranty);
                break;
            case 2:
                System.out.print("Enter Size: ");
                String size = sc.nextLine();
                System.out.print("Enter Material: ");
                String material = sc.nextLine();
                System.out.print("Enter Color: ");
                String color = sc.nextLine();
                product = new Clothing(id, name, price, size, material, color);
                break;
            case 3:
                System.out.print("Enter Weight: ");
                String weight = sc.nextLine();
                System.out.print("Enter Expiration Date (YYYY-MM-DD): ");
                String expDate = sc.nextLine();
                product = new Grocery(id, name, price, weight, expDate);
                break;
            default:
                System.out.println("Invalid category.");
        }

        return product;
    }

    private static Product createProductByCatId(Scanner sc, int id) {
        System.out.println("Select Product Category:");
        System.out.println("1. Electronics");
        System.out.println("2. Clothing");
        System.out.println("3. Grocery");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        Product product = null;

        switch (choice) {
            case 1:
                product = new Electronics(id, "", 0.0, "", "");
                break;
            case 2:
                product = new Clothing(id, "", 0.0, "", "", "");
                break;
            case 3:
                product = new Grocery(id, "", 0.0, "", "");
                break;
            default:
                System.out.println("Invalid category.");
        }

        return product;
    }
}
