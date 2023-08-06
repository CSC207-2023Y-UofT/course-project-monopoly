package presenters;

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

            // Draw the title "Monopoly" at the top middle
            drawGameTitle(g);

            // Draw the overall Monopoly board layout
            drawBoard(g);
        }

        private void drawGameTitle(Graphics g) {
            // Set the title font, style and size
            Font titleFont = new Font("Arial", Font.BOLD, 30);
            g.setFont(titleFont);

            // Get the width and height of the string with the current font to position it correctly
            FontMetrics fm = g.getFontMetrics();
            int titleWidth = fm.stringWidth("Monopoly: Adventure at UofT");
            int titleHeight = fm.getAscent();  // This gives the height from base to top of the font

            // Calculate the position to center the title
            int x = (getWidth() - titleWidth) / 2;
            int y = titleHeight + 20;  // A little padding from the top

            // Set color for the title
            g.setColor(new Color(173, 216, 230));  // Light blue color
            g.drawString("Monopoly: Adventure at UofT", x, y);
        }

        private void drawBoard(Graphics g) {
            // This will be the length of one side of the outer square (board)
            int boardSize = 630;
            // Calculate the starting point to make the board centered
            int x = (getWidth() - boardSize) / 2;
            int y = (getHeight() - boardSize) / 2 + 30; // lower it for the title placement

            // Draw the outer rectangle of the board
            g.setColor(Color.BLACK);
            g.drawRect(x, y, boardSize, boardSize);

            // Draw the GO square on the top middle of the board
            int gridWidth = boardSize / 4 ;
            int goStartX = x + 3 * boardSize / 8;
            drawCornerSpace(g, goStartX, y, "GO"); // Top middle

            // two special grids - top
            //Left
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, 3 * boardSize / 8, boardSize / 5);  // Left side
            // Right
            g.fillRect(goStartX + boardSize / 4, y, 3 * boardSize / 8, boardSize / 5);  // Right side

            int segmentHeight = 4 * boardSize / 60;
            for (int i = 0; i < 11; i++) {  // Start from 1 because the 0th segment is for the GO square
                g.setColor(Color.LIGHT_GRAY);
                int y1 = y + i * segmentHeight + boardSize / 5;
                g.fillRect(x, y1, gridWidth, segmentHeight);  // Left side

                // Adjusted starting point for the right side properties
                g.fillRect(x + gridWidth + boardSize/ 2, y1, gridWidth, segmentHeight);  // Right side

                // Draw separating lines
                g.setColor(Color.BLACK);
                g.drawLine(x, y1, x + gridWidth, y1);  // Left side separators

                // Adjusted starting and ending points for the right side separators
                g.drawLine(x + gridWidth + boardSize / 2, y1, x + boardSize, y1);  // Right side separators
            }

            // Draw the middle rectangle
            int innerRectMargin = boardSize / 5;  // The margin from the top and bottom of the board
            g.setColor(new Color(238, 232, 205));  // A light beige color
            g.fillRect(x + gridWidth, y + innerRectMargin, boardSize / 2, boardSize - boardSize / 5 - 2 * segmentHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x + gridWidth, y + innerRectMargin, boardSize / 2, boardSize - boardSize / 5 - 2 * segmentHeight);

            // two special grids - bottom
            //Left
            g.setColor(Color.YELLOW);
            g.fillRect(x, y + boardSize - 2 * segmentHeight, boardSize / 2, 2 * segmentHeight);  // Left side
            // Right
            g.fillRect(x + boardSize / 2, y + boardSize - 2 * segmentHeight, boardSize / 2, 2 * segmentHeight);  // Right side
            g.setColor(Color.BLACK);
            g.drawLine(x + boardSize / 2, y + boardSize - 2 * segmentHeight, x + boardSize / 2, y + boardSize);

        }

        private void drawCornerSpace(Graphics g, int x, int y, String label) {
            int gridSizeWidth = (label.equals("GO")) ? 630 / 4 : 70;  // Adjust the width based on the label
            int gridSizeHeight = (label.equals("GO")) ? 630 / 5 : 70;  // Adjust the height; reduced height for GO

            g.setColor(Color.WHITE);
            g.fillRect(x, y, gridSizeWidth, gridSizeHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, gridSizeWidth, gridSizeHeight);

            Font titleFont = new Font("Arial", Font.BOLD, 18);
            g.setFont(titleFont);
            int textX = x + (gridSizeWidth - g.getFontMetrics().stringWidth(label)) / 2;  // Center the label
            int textY = y + (gridSizeHeight + g.getFontMetrics().getAscent() - g.getFontMetrics().getDescent()) / 2;
            g.drawString(label, textX, textY);
        }


        private void drawProperties(Graphics g, int x, int y) {
            // Calculate the width of each segment (excluding corners)
            int segmentWidth = 560 / 9;

            // Draw the properties spaces on the top and bottom sides of the board
            for (int i = 0; i < 9; i++) {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(x + 70 + i * segmentWidth, y, segmentWidth, 70); // Top side
                g.fillRect(x + 70 + i * segmentWidth, y + 630, segmentWidth, 70); // Bottom side
            }

            // Draw the properties spaces on the left and right sides of the board
            for (int i = 0; i < 9; i++) {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(x, y + 70 + i * segmentWidth, 70, segmentWidth); // Left side
                g.fillRect(x + 630, y + 70 + i * segmentWidth, 70, segmentWidth); // Right side
            }

            // Drawing the separating lines for the properties
            g.setColor(Color.BLACK);
            for (int i = 1; i < 9; i++) {
                // Top and bottom separating lines
                g.drawLine(x + 70 + i * segmentWidth, y, x + 70 + i * segmentWidth, y + 70);
                g.drawLine(x + 70 + i * segmentWidth, y + 630, x + 70 + i * segmentWidth, y + 700);

                // Left and right separating lines
                g.drawLine(x, y + 70 + i * segmentWidth, x + 70, y + 70 + i * segmentWidth);
                g.drawLine(x + 630, y + 70 + i * segmentWidth, x + 700, y + 70 + i * segmentWidth);
            }
        }


        private void drawDestinyCard(Graphics g, int x, int y) {
            // Calculate the width of each segment (excluding corners)
            int segmentWidth = 560 / 9;

            // Draw the destiny card space at the center of each side of the board
            g.setColor(Color.BLUE);
            g.fillRect(x + 70 + 4 * segmentWidth, y, segmentWidth, 70); // Top side
            g.fillRect(x + 70 + 4 * segmentWidth, y + 630, segmentWidth, 70); // Bottom side
            g.fillRect(x, y + 70 + 4 * segmentWidth, 70, segmentWidth); // Left side
            g.fillRect(x + 630, y + 70 + 4 * segmentWidth, 70, segmentWidth); // Right side

            g.setColor(Color.WHITE);
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth("Destiny");
            int textHeight = fm.getAscent();

            // Centered coordinates for each "Destiny" text
            int xCentered = (segmentWidth - textWidth) / 2;
            int yCentered = (70 + textHeight) / 2;

            g.drawString("Destiny", x + 70 + 4 * segmentWidth + xCentered, y + yCentered); // Top side
            g.drawString("Destiny", x + 70 + 4 * segmentWidth + xCentered, y + 630 + yCentered); // Bottom side
            g.drawString("Destiny", x + xCentered, y + 70 + 4 * segmentWidth + yCentered - fm.getDescent()); // Left side
            g.drawString("Destiny", x + 630 + xCentered, y + 70 + 4 * segmentWidth + yCentered - fm.getDescent()); // Right side
        }

    }

    private class LeftSidebar extends JPanel {
        private static final int SIDEBAR_WIDTH = 250;
        private static final int THREAD_SECTION_HEIGHT = 900 / 2;  // bottom half of the sidebar
        private static final int THREAD_TITLE_PADDING = 20;  // distance from the top of the thread section

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the left sidebar
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, SIDEBAR_WIDTH, getHeight());

            // Isolate and draw the game thread section at the bottom
            int threadStartY = getHeight() - THREAD_SECTION_HEIGHT;
            g.setColor(Color.WHITE);
            g.drawRect(0, threadStartY, SIDEBAR_WIDTH, THREAD_SECTION_HEIGHT);

            // Font
            Font titleFont = new Font("Arial", Font.BOLD, 18); // "Arial" font, bold style, size 18
            g.setFont(titleFont);

            // Draw "Game thread" title
            g.setColor(Color.WHITE); // Set color to white
            g.drawString("Game thread", SIDEBAR_WIDTH / 4, threadStartY + THREAD_TITLE_PADDING);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(SIDEBAR_WIDTH, getHeight());
        }
    }


    private class RightSidebar extends JPanel {
        private static final int SIDEBAR_WIDTH = 250;
        private static final int TITLE_PADDING = 20;  // distance from the top of the sidebar

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the right sidebar
            g.setColor(Color.MAGENTA);
            g.fillRect(0, 0, SIDEBAR_WIDTH, getHeight());

            // Set a new font with the desired size for the title
            Font titleFont = new Font("Arial", Font.BOLD, 18); // "Arial" font, bold style, size 18
            g.setFont(titleFont);

            // Draw "Players' Details" title
            g.setColor(Color.WHITE); // Set color to white for better contrast against the blue background
            g.drawString("Players' Details", SIDEBAR_WIDTH / 4, TITLE_PADDING);
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


