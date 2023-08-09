package presenters;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;


/**
 * GameBoard class for managing and rendering the game GUI.
 * This class extends JFrame and handles the main game board display, background images, player text areas,
 * and game-related drawings.
 */
public class GameBoard extends JFrame {
    private BufferedImage backgroundImage;
    private static final int SIDEBAR_WIDTH = 320;
    private static final int THREAD_SECTION_HEIGHT = 200;  // bottom half of the sidebar

    private List<JTextArea> playerTextAreas;


    /**
     * Constructor for GameBoard.
     * Initializes the background image and sets up the main frame, game thread text area, and player text areas.
     */
    public GameBoard() {
        // Set the frame size
        setSize(1440, 810);
        // Center the frame on the screen
        setLocationRelativeTo(null);
        loadBackgroundImage();

        // Set up the main frame
        setUndecorated(true); // Remove window decorations
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the custom panel with the background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                drawGameTitle(g);
                drawBlocks(g);
            }
        };
        setContentPane(panel);

        setLayout(null);
        setUpGameThreadTextArea();
        setUpPlayerTextAreas();
    }

    /**
     * Loads the background image from the file system.
     */
    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("data/images/background/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets up the game thread text area with custom styling.
     */
    private void setUpGameThreadTextArea() {
        // Code to set up gameThreadTextArea and its scrollPane
        JTextArea gameThreadTextArea = new JTextArea();
        OutputPresenter.setGameThreadTextArea(gameThreadTextArea); // Set the JTextArea in OutputPresenter

        gameThreadTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        gameThreadTextArea.setForeground(Color.WHITE);
        gameThreadTextArea.setBounds(18, 525, SIDEBAR_WIDTH - 35, THREAD_SECTION_HEIGHT + 75);
        gameThreadTextArea.setEditable(false);
        gameThreadTextArea.setLineWrap(true);
        gameThreadTextArea.setOpaque(false); // Set the opaque property to false
        gameThreadTextArea.setBackground(new Color(0, 0, 0, 0)); // Transparent background

        JScrollPane scrollPane = new JScrollPane(gameThreadTextArea);
        scrollPane.setBounds(18, 525, SIDEBAR_WIDTH - 35, THREAD_SECTION_HEIGHT + 75);
        scrollPane.setOpaque(false); // Make the scroll pane non-opaque
        scrollPane.getViewport().setOpaque(false); // Make the viewport non-opaque
        scrollPane.setBorder(null); // Remove the border
        scrollPane.getViewport().setBackground(new Color(0, 0, 0, 0)); // Set viewport background to transparent

        add(scrollPane);
    }

    /**
     * Initializes and configures the JTextAreas for each player.
     */
    private void setUpPlayerTextAreas() {
        // Code to initialize and configure the JTextAreas for each player
        playerTextAreas = new ArrayList<>();

        // Initialize and configure the JTextAreas for each player
        for (int i = 0; i < 4; i++) {
            JTextArea playerTextArea = new JTextArea();
            playerTextArea.setFont(new Font("Arial", Font.PLAIN, 20));
            playerTextArea.setEditable(false);
            playerTextArea.setLineWrap(true);
            playerTextArea.setBounds(18, 30 + i * 120, 160, 100); // Adjust positions as needed
            add(playerTextArea);
            playerTextAreas.add(playerTextArea);
        }
        PlayerInfoPanel.setallplayerTextAreas(playerTextAreas);

        // Create a new panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Set the FlowLayout to align to the right
        buttonPanel.setBounds(getWidth() - 200, 10, 180, 40); // Increase the width
    }


    /**
     * Sets up the button panel
     */
    private void setUpButtonPanel() {
        // Code to set up the button panel if needed
    }

    /**
     * Draws an image on the Graphics context at specified coordinates.
     */
    private void drawImage(Graphics g, BufferedImage image, int x, int y) {
        g.drawImage(image, x, y, this); // Draw the image at the specified coordinates
    }


    /**
     * Draws blocks on the Graphics context.
     */
    private void drawBlocks(Graphics g) {
        // Draw the blocks
    }

    /**
     * Draws the game title on the Graphics context.
     */
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


    /**
     * Draws the game thread title on the Graphics context.
     */
    private void drawGameThreadTitle(Graphics g) {
        // Set the title font, style and size
        Font titleFont = new Font("Arial", Font.BOLD, 15);
        g.setFont(titleFont);

        // Get the width and height of the string with the current font to position it correctly
        FontMetrics fm = g.getFontMetrics();
        int titleWidth = fm.stringWidth("Game Thread");

        // Calculate the position to center the title
        int x = 150 - titleWidth / 2 ;
        int y = 525;  // A little padding from the top

        // Set color for the title
        g.setColor(new Color(173, 216, 230));  // Light blue color
        g.drawString("Game Thread", x, y);
    }

    /**
     * Main method to run the GameBoard; Just for testing
     */
    public static void main(String[] args) {
        // Create an instance of GameBoard class
        javax.swing.SwingUtilities.invokeLater(() -> {
            GameBoard frame = new GameBoard();
            frame.setVisible(true);

            //These are just for testing, remember to use javax.swing.SwingUtilities.invokeLater(() ->
            // in the main loop
            int currentPlayerId = 1;
            String blockName = "BA";
            int currentPrice = 50;
            int currentLevel = 3;
            String propName = "Boardwalk";
            int tax = 150;
            int ownerId = 2;
            String message = "You received a Destiny card!";

            //Note from Noah:
            // Example method calls from the OutputPresenter class
            OutputPresenter.notifyStartOfRound();
            OutputPresenter.notifyTurn(currentPlayerId);
            OutputPresenter.notifyMovement(currentPlayerId, blockName);
            OutputPresenter.notifyOwnerUpgraded(currentPlayerId, "invested", propName, currentPrice, currentLevel);
            OutputPresenter.notifyOwnerIgnored(currentPlayerId, propName);
            OutputPresenter.notifyPasserbyPaid(currentPlayerId, tax, ownerId, propName);
            OutputPresenter.notifyMaxLevel(propName);
            OutputPresenter.notifyInsufficientFund();
            OutputPresenter.notifyPassingGO(currentPlayerId);
            OutputPresenter.notifyGoToExam(currentPlayerId);
            OutputPresenter.notifyRemainingStopRounds(currentPlayerId, 2);
            OutputPresenter.notifyDestiny(message);

            PlayerInfoPanel.updatePanel(1, 1000);
            PlayerInfoPanel.updatePanel(2, 1000);
            PlayerInfoPanel.updatePanel(2, 3000);

        });
    }

}

