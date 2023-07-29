package UI;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame {
    public GameBoard() {
        // Set basic properties of the window
        setTitle("My Game Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900); // Adjust the size according to game's needs
        setLocationRelativeTo(null); // Center the window on the screen

        // Add game board components or other GUI elements here

        // Create left and right sidebars
        JPanel leftSidebar = new LeftSidebar();
        JPanel gameBoardPanel = new GameBoardPanel();
        JPanel rightSidebar = new RightSidebar();

        // Use BorderLayout to arrange the components
        setLayout(new BorderLayout());

        // Add the sidebars and the game board to the frame
        add(leftSidebar, BorderLayout.WEST);
        add(gameBoardPanel, BorderLayout.CENTER);
        add(rightSidebar, BorderLayout.EAST);

        // Make the window visible
        setVisible(true);
    }

    private class GameBoardPanel extends JPanel {
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

    private class LeftSidebar extends JPanel {
        private static final int SIDEBAR_WIDTH = 250;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the left sidebar
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, SIDEBAR_WIDTH, getHeight());
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(SIDEBAR_WIDTH, getHeight());
        }
    }

    private class RightSidebar extends JPanel {
        private static final int SIDEBAR_WIDTH = 250;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the right sidebar
            g.setColor(Color.RED);
            g.fillRect(0, 0, SIDEBAR_WIDTH, getHeight());
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(SIDEBAR_WIDTH, getHeight());
        }
    }

    public static void main(String[] args) {
        // Create an instance of GameBoard class
        javax.swing.SwingUtilities.invokeLater(() -> {
            new GameBoard();
        });
    }
}
