package HW.CoffeeShop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/coffeeshop";
            String user = "test";
            String password = "test";
            conn = DriverManager.getConnection(url, user, password);


            System.out.println("Connected to database!");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return conn;
    }

}