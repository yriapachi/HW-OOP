package Assignment1.b;

import Assignment1.c.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Product {
    protected int id;
    protected String name;
    protected double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


    public abstract String addProduct();

    public abstract void modProduct();

    protected abstract String getTableName();

    public String searchProduct(String search) {
        Connection conn = DBConnection.getConnection();

        String checkQuery = "SELECT * FROM " + getTableName() + " WHERE name LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(checkQuery)) {
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Product found: " + rs.getString("name"));
            } else {
                System.out.println("No such product");
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return "Error during search. Please try again.";
        }

        return search;
    }






}
