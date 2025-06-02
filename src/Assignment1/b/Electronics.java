package Assignment1.b;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Electronics extends Product {
    protected String brandName;
    protected String warrPeriod;

    public Electronics(int id, String name, double price, String brandName, String warrPeriod) {
        super(id, name, price);
        this.brandName = brandName;
        this.warrPeriod = warrPeriod;
    }

    @Override
    public String addProduct() {
        Connection conn = DBConnection.getConnection();
        String insertQuery = "INSERT INTO electronics (id, name, price, brandName, warrPeriod) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {
            ps.setInt(1, this.id);
            ps.setString(2, this.name);
            ps.setDouble(3, this.price);
            ps.setString(4, this.brandName);
            ps.setString(5, this.warrPeriod);

            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Product added successfully.");
                return "Success";
            } else {
                System.out.println("Failed to add product.");
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
        sc.nextLine();  // consume newline
        System.out.println("Enter new brand name: ");
        String newBrand = sc.nextLine();
        System.out.println("Enter new warranty period: ");
        String newWarr = sc.nextLine();

        String updateQuery = "UPDATE electronics SET name = ?, price = ?, brandName = ?, warrPeriod = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(updateQuery)) {
            ps.setString(1, newName);
            ps.setDouble(2, newPrice);
            ps.setString(3, newBrand);
            ps.setString(4, newWarr);
            ps.setInt(5, this.id);

            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Product updated successfully.");
                this.name = newName;
                this.price = newPrice;
                this.brandName = newBrand;
                this.warrPeriod = newWarr;
            } else {
                System.out.println("No product found with given ID.");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    protected String getTableName() {
        return "electronics";
    }
}

