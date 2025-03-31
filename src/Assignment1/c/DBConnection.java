package Assignment1.c;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/hangman";
    private static final String username = "java";
    private static final String password = "1111";

    public static void getConnection() {
        try{
            Connection conn = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("Done!");


        }catch(SQLException se){
            System.out.println(se.getMessage());}
    }

}
