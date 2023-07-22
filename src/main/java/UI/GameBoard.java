package UI;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame {
    public GameBoard() {
        // Set basic properties of the window
        setTitle("My Game Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900); // Adjust the size according to your game's needs
        setLocationRelativeTo(null); // Center the window on the screen

        // Add game board components or other GUI elements here
        add(new DrawingPanel());

        // Make the window visible
        setVisible(true);
    }

    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Calculate the starting point to make the square centered
            int x = (getWidth() - 600) / 2;
            int y = (getHeight() - 600) / 2;

            // Draw the square
            g.drawRect(x, y, 600, 600);
        }
    }

    public static void main(String[] args) {
        // Create an instance of your GameBoard class
        javax.swing.SwingUtilities.invokeLater(() -> {
            new GameBoard();
        });
    }
}
