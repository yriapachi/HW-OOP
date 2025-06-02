package Assignment1.c;

import javax.swing.*;
import java.awt.*;

public class HangmanDesign extends JFrame {
    private static final Color lightPink = new Color(255, 182, 193);
    private static final Color darkPink = new Color(255, 110, 120);

    public static JFrame window;
    public static JLabel wordLabel;
    public static JLabel statsLabel;
    public static StringBuilder guessedLetters = new StringBuilder();
    public static StringBuilder incorrectLetters = new StringBuilder();

    public static int wins = 0;
    public static int losses = 0;
    public static int currentStreak = 0;
    public static int highestStreak = 0;

    public HangmanDesign() {
        window = new JFrame("Hangman");
        window.setSize(800, 600);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout());

        wordLabel = new JLabel("", SwingConstants.CENTER);
        wordLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
        wordLabel.setForeground(darkPink);

        statsLabel = new JLabel("", SwingConstants.CENTER);
        statsLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        statsLabel.setForeground(darkPink);

        hangmanDesign();
    }

    public static void hangmanDesign() {
        Implement g = new Implement();

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(lightPink);

        JPanel eastPanel = new JPanel(new BorderLayout());
        eastPanel.setBackground(lightPink);
        eastPanel.add(wordLabel, BorderLayout.CENTER);
        eastPanel.add(statsLabel, BorderLayout.SOUTH);

        g.setBackground(lightPink);
        g.setForeground(darkPink);

        centerPanel.add(g, BorderLayout.WEST);
        centerPanel.add(eastPanel, BorderLayout.CENTER);

        JPanel keyboardPanel = new JPanel();
        keyboardPanel.setLayout(new BoxLayout(keyboardPanel, BoxLayout.Y_AXIS));
        keyboardPanel.setBackground(lightPink);

        String[] rows = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
        for (String row : rows) {
            JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            rowPanel.setBackground(lightPink);
            for (char letter : row.toCharArray()) {
                JButton button = new JButton(String.valueOf(letter));
                button.setBackground(darkPink);
                button.setForeground(Color.PINK);
                button.setFont(new Font("SansSerif", Font.BOLD, 16));
                button.setFocusPainted(false);
                rowPanel.add(button);

                button.addActionListener(e -> {
                    String guessed = e.getActionCommand();
                    Implement.processGuess(guessed);
                    g.repaint();
                });
            }
            keyboardPanel.add(rowPanel);
        }

        JButton resetButton = new JButton("Reset");
        resetButton.setBackground(darkPink);
        resetButton.setForeground(Color.PINK);
        resetButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        resetButton.addActionListener(e -> {
            Implement.resetGame();
            g.repaint();
        });

        JPanel resetPanel = new JPanel(new BorderLayout());
        resetPanel.setBackground(lightPink);
        resetPanel.add(resetButton, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(lightPink);
        southPanel.add(keyboardPanel, BorderLayout.CENTER);
        southPanel.add(resetPanel, BorderLayout.SOUTH);

        JPanel northPanel = new JPanel();
        northPanel.setBackground(lightPink);

        window.add(northPanel, BorderLayout.NORTH);
        window.add(centerPanel, BorderLayout.CENTER);
        window.add(southPanel, BorderLayout.SOUTH);

        updateStatsLabel();

        window.validate();
        window.repaint();

        window.setVisible(true);
    }

    public static void updateStatsLabel() {
        statsLabel.setText(
                "Wins: " + wins +
                        " | Losses: " + losses +
                        " | Current Streak: " + currentStreak +
                        " | Highest Streak: " + highestStreak +
                        " | Wrong: " + incorrectLetters.toString()
        );
    }
}
