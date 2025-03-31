package HW.CoffeeShop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBReader {
    public static void dbRead(Connection conn) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter date: ");
            String date = sc.nextLine();
            String query = "SELECT * FROM orders WHERE date = '" + date + "';";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Done!");
            while (rs.next()) {
                String customerName = rs.getString("customer_name");
                String order_name = rs.getString("order_name");
                double price = rs.getDouble("price");
                String orderDate = rs.getString("date");

                System.out.println("Customer: " + customerName +
                        ", Order: " + order_name +
                        ", Price: " + price +
                        ", Date: " + orderDate);
            }
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
    }
}
