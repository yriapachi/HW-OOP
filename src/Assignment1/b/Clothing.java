package Assignment1.b;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Clothing extends Product {
    protected String size;
    protected String material;
    protected String color;

    public Clothing(int id, String name, double price, String size, String material, String color) {
        super(id, name, price);
        this.size = size;
        this.material = material;
        this.color = color;
    }

    @Override
    public String addProduct() {
        Connection conn = DBConnection.getConnection();
        String insertQuery = "INSERT INTO clothing (id, name, price, size, material, color) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {
            ps.setInt(1, this.id);
            ps.setString(2, this.name);
            ps.setDouble(3, this.price);
            ps.setString(4, this.size);
            ps.setString(5, this.material);
            ps.setString(6, this.color);

            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Clothing product added successfully.");
                return "Success";
            } else {
                System.out.println("Failed to add clothing product.");
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
        System.out.println("Enter new size: ");
        String newSize = sc.nextLine();
        System.out.println("Enter new material: ");
        String newMaterial = sc.nextLine();
        System.out.println("Enter new color: ");
        String newColor = sc.nextLine();

        String updateQuery = "UPDATE clothing SET name = ?, price = ?, size = ?, material = ?, color = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(updateQuery)) {
            ps.setString(1, newName);
            ps.setDouble(2, newPrice);
            ps.setString(3, newSize);
            ps.setString(4, newMaterial);
            ps.setString(5, newColor);
            ps.setInt(6, this.id);

            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Clothing product updated successfully.");
                this.name = newName;
                this.price = newPrice;
                this.size = newSize;
                this.material = newMaterial;
                this.color = newColor;
            } else {
                System.out.println("No product found with given ID.");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    protected String getTableName() {
        return "clothing";
    }
}
