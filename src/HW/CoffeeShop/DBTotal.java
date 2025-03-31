package HW.CoffeeShop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBTotal {
    public static void dbTotalPaid(Connection conn) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter customer name: ");
            String customer_name = sc.nextLine();
            String query = "select customer_name, sum(price) as total_paid from orders where customer_name='" + customer_name + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("customer_name") +
                        " price: " + rs.getDouble("total_paid"));
            }
            System.out.println("DONE");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

}
