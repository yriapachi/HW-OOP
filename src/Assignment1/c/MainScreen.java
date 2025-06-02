package Assignment1.c;

import javax.swing.*;
import java.awt.*;

public class MainScreen {
    public static final Color pastelPink = new Color(255, 182, 193);

    public static void mainScreen() {
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);


        JPanel northPanel = new JPanel();
        JPanel centerPanel = new JPanel();


        JLabel northLabel = new JLabel("Hangman");
        northLabel.setFont(new Font("Arial", Font.BOLD, 30));


        northPanel.setBackground(pastelPink);
        northPanel.add(northLabel, BorderLayout.CENTER);

        JButton loginButton = new JButton("Log in");

        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(pastelPink);

        JButton signupButton = new JButton("Sign up");

        signupButton.setBackground(Color.BLACK);
        signupButton.setForeground(pastelPink);

        centerPanel.add(loginButton, BorderLayout.EAST);
        centerPanel.add(signupButton, BorderLayout.WEST);

        loginButton.addActionListener(e -> {
            Login.loginPage();
        });

        signupButton.addActionListener(e -> {
            Signup.signupPage();
        });

        JLabel centerLabel= new JLabel("");
        centerPanel.add(centerLabel);
        window.add(northPanel, BorderLayout.NORTH);
        window.add(centerPanel, BorderLayout.CENTER);
        window.setVisible(true);


    }
}
