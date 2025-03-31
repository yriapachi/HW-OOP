package Assignment1.c;

import java.sql.*;
import java.util.Scanner;

public class Signup {

    public static void signup(Connection conn) {
        Scanner sc = new Scanner(System.in);
        String username;
        String password;

        while (true) {
            System.out.println("Enter your username: ");
            username = sc.nextLine();
            System.out.println("Enter your password: ");
            password = sc.nextLine();

            String query = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, username);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Username already exists! Log in with existing account? y/n");
                        String answer = sc.nextLine();
                        if (answer.equalsIgnoreCase("y")) {
                            Login.login(conn);
                            return;
                        } else {
                            System.out.println("Please choose a new username.");
                            continue;
                        }
                    } else {
                        String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
                        try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {
                            ps.setString(1, username);
                            ps.setString(2, password);
                            ps.executeUpdate();
                            System.out.println("Account created successfully!");
                            System.out.println("Welcome! Play? y/n");
                            String answer = sc.nextLine();
                            if (answer.equalsIgnoreCase("y")) {
                                Hangman.hangman();
                            }
                            return;
                        }
                    }
                }
            } catch (SQLException se) {
                System.err.println(se.getMessage());
            }
        }
    }
}