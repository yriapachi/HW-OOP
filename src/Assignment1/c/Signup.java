package Assignment1.c;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Signup {
    public static JLabel usernameLabel;
    public static JLabel passwordLabel;
    public static JTextField usernameField;
    public static JPasswordField passwordField;
    public static JButton signupButton;
    public static JButton backButton;

    public static void signupPage() {

        JFrame frame = new JFrame("Signup");

        frame.setTitle("Signup Page");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        centerPanel.setLayout(new GridLayout(2, 2, 5, 5));
        centerPanel.setBackground(new Color(255, 182, 193));

        usernameLabel = new JLabel("Write a creative username!");
        usernameField = new JTextField(20);

        passwordLabel = new JLabel("Enter a password:");
        passwordField = new JPasswordField(20);

        signupButton = new JButton("Sign Up");
        signupButton.setBackground(Color.BLACK);
        signupButton.setForeground(Color.pink);
        signupButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                String result = executeSignup(frame);
                if (result != null) {
                    JOptionPane.showMessageDialog(frame, result);
                    if (result.startsWith("Signup successful")) {
                        frame.dispose();
                    }
                }
            });
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

        buttonPanel.add(signupButton);
        buttonPanel.add(backButton);

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }


    public static String executeSignup(JFrame frame) {
        Connection conn = DBConnection.getConnection();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());


        if (password.isEmpty()) {
            return "Password must be filled!";
        }

        if (username == null || username.isEmpty()) {
            username = "user" + System.currentTimeMillis();
        }

        String checkQuery = "select * from userdata where username = ?";
        try (PreparedStatement ps = conn.prepareStatement(checkQuery)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int choice = JOptionPane.showOptionDialog(
                        frame,
                        "The username already exists! Would you like to log in with this account or try a different username?",
                        "Username Taken",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new Object[]{"Login", "Try Another Username"},
                        "Try Another Username");

                if (choice == JOptionPane.YES_OPTION) {
                    Login.loginPage();
                    frame.dispose();
                } else {
                    usernameField.setText("");
                    passwordField.setText("");
                }

                return null;
            }

            String insertQuery = "insert into userdata (username, password, wins, losses, cur_streak, best_streak) values (?, ?, 0, 0, 0, 0)";
            try (PreparedStatement insertPs = conn.prepareStatement(insertQuery)) {
                insertPs.setString(1, username);
                insertPs.setString(2, password);
                insertPs.executeUpdate();

                HangmanDesign hangmanDesign = new HangmanDesign();
                HangmanDesign.hangmanDesign();

                return "Signup successful! Welcome, " + username + "!";
            }

        } catch (SQLException se) {
            se.printStackTrace();
            return "Error during signup. Please try again.";
        }
    }
}
