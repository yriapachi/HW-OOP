package HW.CoffeeShop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DBWriter {
    public static void dbWrite(Connection conn) {
       try {
           Scanner sc = new Scanner(System.in);
           System.out.println("Enter customer name: ");
           String customer_name = sc.nextLine();
           System.out.println("Enter order name: ");
           String order_name = sc.nextLine();
           System.out.println("Enter price: ");
           double price = sc.nextDouble();
           sc.nextLine();
           System.out.println("Enter date: ");
           String date = sc.nextLine();

           String query = "insert into orders(customer_name,order_name,price,date) values(?,?,?,?)";
           PreparedStatement ps = conn.prepareStatement(query);
           ps.setString(1, customer_name);
           ps.setString(2, order_name);
           ps.setDouble(3, price);
           ps.setString(4, date);
           ps.executeUpdate();

           System.out.println("Order added successfully");
       }catch(SQLException se){
           System.err.println(se.getMessage());
       }


    }
}
