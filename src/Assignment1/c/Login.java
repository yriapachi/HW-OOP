package Assignment1.c;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public static JLabel usernameLabel;
    public static JLabel passwordLabel;
    public static JTextField usernameField;
    public static JPasswordField passwordField;
    public static JButton loginButton;
    public static JButton backButton;

    public static void loginPage() {
        JFrame frame = new JFrame("Login");
        frame.setTitle("Login Page");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        centerPanel.setLayout(new GridLayout(2, 2, 5, 5));
        centerPanel.setBackground(new Color(255, 182, 193));

        usernameLabel = new JLabel("Enter your username:");
        usernameField = new JTextField(20);

        passwordLabel = new JLabel("Enter your password:");
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.pink);

        loginButton.addActionListener(e -> {
            String result = executeLogin(frame);
            if (result != null) {
                JOptionPane.showMessageDialog(frame, result);
                if (result.startsWith("Login successful")) {
                    frame.dispose();
                }
            }
        });

        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.pink);
        backButton.addActionListener(e -> {
            frame.dispose();
        });

        centerPanel.add(usernameLabel);
        centerPanel.add(usernameField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);

        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static String executeLogin(JFrame frame) {
        Connection conn = DBConnection.getConnection();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());


        String checkQuery = "select password from userdata where username = ?";
        try (PreparedStatement ps = conn.prepareStatement(checkQuery)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (password.equals(storedPassword)) {
                    HangmanDesign.hangmanDesign();
                    return "Login successful, welcome back " + username + "!";

                } else {
                    usernameField.setText("");
                    passwordField.setText("");
                    return "Login failed. Incorrect password.";
                }
            } else {
                Signup.signupPage();
                return "Username not found. You can sign up with a new account though!";
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return "Error during login. Please try again.";
        }
    }

}
