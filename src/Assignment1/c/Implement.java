package Assignment1.c;

import javax.swing.*;
import java.awt.*;

public class Implement extends JPanel {
    private static final Color lightPink = new Color(255, 182, 193);
    private static final Color darkPink = new Color(255, 110, 120);
    public static String fullWord = RandomWordGen.getWord();
    public static int wrongGuesses = 0;
    private static final int maxWrongGuesses = 6;

    public Implement() {
        setPreferredSize(new Dimension(300, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHangman(g);
    }

    public void drawHangman(Graphics g) {
        g.setColor(darkPink);
        g.drawLine(50, 250, 150, 250);
        g.drawLine(100, 250, 100, 50);
        g.drawLine(100, 50, 200, 50);
        g.drawLine(200, 50, 200, 80);

        if (wrongGuesses > 0) g.drawOval(175, 80, 50, 50);        // Head
        if (wrongGuesses > 1) g.drawLine(200, 130, 200, 190);      // Body
        if (wrongGuesses > 2) g.drawLine(200, 140, 170, 170);      // Left arm
        if (wrongGuesses > 3) g.drawLine(200, 140, 230, 170);      // Right arm
        if (wrongGuesses > 4) g.drawLine(200, 190, 170, 220);      // Left leg
        if (wrongGuesses > 5) g.drawLine(200, 190, 230, 220);      // Right leg
    }

    public static void processGuess(String letter) {
        if (HangmanDesign.guessedLetters.indexOf(letter) >= 0) return;

        HangmanDesign.guessedLetters.append(letter);

        if (!fullWord.toUpperCase().contains(letter)) {
            wrongGuesses++;
            HangmanDesign.incorrectLetters.append(letter).append(" ");
        }

        updateWordDisplay();
    }

    public static void updateWordDisplay() {
        StringBuilder display = new StringBuilder();
        boolean won = true;

        for (int i = 0; i < fullWord.length(); i++) {
            char c = fullWord.charAt(i);
            String cStr = String.valueOf(c).toUpperCase();
            if (HangmanDesign.guessedLetters.indexOf(cStr) >= 0) {
                display.append(c).append(" ");
            } else {
                display.append("- ");
                won = false;
            }
        }

        HangmanDesign.wordLabel.setText(display.toString().trim());

        if (won) {
            JOptionPane.showMessageDialog(null, "You Win! The word was: " + fullWord, "Victory", JOptionPane.INFORMATION_MESSAGE);
            HangmanDesign.wins++;
            HangmanDesign.currentStreak++;
            if (HangmanDesign.currentStreak > HangmanDesign.highestStreak) {
                HangmanDesign.highestStreak = HangmanDesign.currentStreak;
            }
            resetGame();
        } else if (wrongGuesses >= maxWrongGuesses) {
            JOptionPane.showMessageDialog(null, "You Lose! The word was: " + fullWord, "Game Over", JOptionPane.INFORMATION_MESSAGE);
            HangmanDesign.losses++;
            HangmanDesign.currentStreak = 0;
            resetGame();
        }

        HangmanDesign.updateStatsLabel();
    }

    public static void resetGame() {
        fullWord = RandomWordGen.getWord();
        HangmanDesign.guessedLetters.setLength(0);
        HangmanDesign.incorrectLetters.setLength(0);
        wrongGuesses = 0;
        updateWordDisplay();
    }
}