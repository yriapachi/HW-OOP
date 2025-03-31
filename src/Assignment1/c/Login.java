package Assignment1.c;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    public static void login(Connection conn) {
        Scanner sc = new Scanner(System.in);

        System.out.println("LOGIN");
        System.out.print("Enter your username: ");
        String username = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                int attempts = 5;
                while (attempts > 0) {
                    if (storedPassword.equals(password)) {
                        System.out.println("Successful login! Welcome, " + username + "!");
                        System.out.print("Play? y/n: ");
                        String answer = sc.nextLine();
                        if (answer.equalsIgnoreCase("y")) {
                            Hangman hangman = new Hangman();
                            Hangman.hangman();
                        }
                        return;
                    } else {
                        attempts--;
                        if (attempts == 0) {
                            System.out.println("Ran out of attempts. Please try again later.");
                            return;
                        } else {
                            System.out.println("Wrong password! You have " + attempts + " attempts left.");
                            password = sc.nextLine();
                        }
                    }
                }
            } else {
                System.out.println("Account doesn't exist. Create one? y/n");
                String answer = sc.nextLine();
                if (answer.equalsIgnoreCase("y")) {
                    Signup.signup(conn);
                }
            }
        } catch (SQLException se) {
            System.err.println(se.getMessage());
        }
    }
}