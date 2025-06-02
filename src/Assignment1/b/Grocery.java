package Assignment1.b;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Grocery extends Product {
    protected String weight;
    protected String expirationDate;

    public Grocery(int id, String name, double price, String weight, String expirationDate) {
        super(id, name, price);
        this.weight = weight;
        this.expirationDate = expirationDate;
    }

    @Override
    public String addProduct() {
        Connection conn = DBConnection.getConnection();
        String insertQuery = "INSERT INTO grocery (id, name, price, weight, expirationDate) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {
            ps.setInt(1, this.id);
            ps.setString(2, this.name);
            ps.setDouble(3, this.price);
            ps.setString(4, this.weight);
            ps.setString(5, this.expirationDate);

            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Grocery product added successfully.");
                return "Success";
            } else {
                System.out.println("Failed to add grocery product.");
                return "Failure";
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return "Error";
        }
    }

    @Override
    public void modProduct() {
        Connection conn = DBConnection.getConnection();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter new name: ");
        String newName = sc.nextLine();
        System.out.println("Enter new price: ");
        double newPrice = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter new weight: ");
        String newWeight = sc.nextLine();
        System.out.println("Enter new expiration date (YYYY-MM-DD): ");
        String newExpirationDate = sc.nextLine();

        String updateQuery = "UPDATE grocery SET name = ?, price = ?, weight = ?, expirationDate = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(updateQuery)) {
            ps.setString(1, newName);
            ps.setDouble(2, newPrice);
            ps.setString(3, newWeight);
            ps.setString(4, newExpirationDate);
            ps.setInt(5, this.id);

            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Grocery product updated successfully.");
                this.name = newName;
                this.price = newPrice;
                this.weight = newWeight;
                this.expirationDate = newExpirationDate;
            } else {
                System.out.println("No product found with given ID.");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    protected String getTableName() {
        return "grocery";
    }
}

