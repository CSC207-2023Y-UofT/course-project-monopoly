package presenters;
import datagateways.DataManager;
import datagateways.DestinyCardDataManager;
import entities.Block;
import entities.GameData;
import entities.Player;
import entities.Property;
import usecases.StatusChecker;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;


/**
 * GameBoard class for managing and rendering the game GUI.
 * This class extends JFrame and handles the main game board display, background images, player text areas,
 * and game-related drawings.
 */
public class GameBoard extends JFrame{
    private final String DEFAULT_FONT = "Arial";
    private final int DEFAULT_FONT_SIZE = 20;

    private BufferedImage backgroundImage;
    private BufferedImage interactivePanelImage;
    private static final int SIDEBAR_WIDTH = 400;
    private static final int THREAD_SECTION_HEIGHT = 280;  // bottom half of the sidebar

    private static final int STARTING_BLOCK_ID = 100;
    private static final int BLOCKS_COUNT = 27;

    private final String POSITION_FILENAME = "data/develop/player_location.csv";

    // The first Hash Map: For file name : image
    public HashMap<String, BufferedImage> blocks;
    public HashMap<String, BufferedImage> images;

    // Second Hash Map: For Block ID: Location
    private final HashMap<Integer, ArrayList<Integer>> blockLocations;


    // PlayerID: Hashmap<BlockID: Location>
    private final HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> playerLocation;

    private final HashMap<Integer, ArrayList<Integer>> playerPosition;
    // Some configurations

    // Replace with your directory path
    private final String BLOCK_PATH = "data/images/blocks";
    private final String PLAYER_PATH = "data/images/players";
    private final String ROLL_PATH = "data/images/interactive";
    private final String INTERACTIVE_PATH = "data/images/infopanel";
    private final String BACKGROUND_IMAGE_PATH = "data/images/background/background.png";
    // Add more extensions if needed
    final List<String> VALID_IMAGE_FORMAT = List.of(new String[]{".png", ".jpg"});

    public void loadImages(String filename)
    {
        File folder = new File(filename);
        File[] listOfFiles = folder.listFiles();

        // We would assume that the path does contain something
        // So an assert statement is needed
        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile() && isValidFormat(file, VALID_IMAGE_FORMAT)) {
                try {
                    BufferedImage img = ImageIO.read(file);
                    String nameWithoutExtension = file.getName()
                            .substring(0, file.getName().lastIndexOf('.'));
                    images.put(nameWithoutExtension, img);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Constructor for GameBoard.
     * Initializes the background image and sets up the main frame, game thread text area, and player text areas.
     */
    public GameBoard() {

        // Set the frame size
        setSize(1920, 1080);
        setResizable(false); // Disable window resizing

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Set up the main frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            backgroundImage = ImageIO.read(new File(BACKGROUND_IMAGE_PATH));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }



        // Setup two hash maps
        // Mapping from String to BufferedImage
        images = new HashMap<>();
        blockLocations = new HashMap<>();
        // Initialize player location.(place that players can move to)
        playerLocation = new HashMap<>();
        // Initialize player current place
        playerPosition = new HashMap<>();

        DataManager<String> manager = new DestinyCardDataManager();
        ArrayList<String[]> positions = manager.readData(POSITION_FILENAME);

        for (String[] row: positions) {
            int playerId = Integer.parseInt(row[0]);
            if (!playerLocation.containsKey(playerId))
                playerLocation.put(playerId, new HashMap<>());
            playerLocation.get(playerId).put(Integer.parseInt(row[1]),
                    new ArrayList<>(Arrays.asList(
                            Integer.parseInt(row[2]),
                            Integer.parseInt(row[3])
                    )));
        }


        //initialize the player current position
        for (int i = 1; i <= 4; i++) {
            playerPosition.put(i, playerLocation.get(i).get(STARTING_BLOCK_ID));
        }

        // load all the image files
        loadImages(BLOCK_PATH);
        loadImages(PLAYER_PATH);
        loadImages(ROLL_PATH);
        loadImages(INTERACTIVE_PATH);

        interactivePanelImage = images.get("p1_roll");


        blocks = new HashMap<>();
        blocks.putAll(images);

        // Paint the board
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                drawBlocks(g);

                drawPlayers(g);
                drawInteractivePanel(g);
                drawPlayersInfoPanel(g);

            }
        };


        setContentPane(panel);
        setUpPlayerTextAreas();

        // Add the Text Area
        setLayout(null);
        setUpGameThreadTextArea();

        // Ensure the JFrame can receive key events
        this.setFocusable(true);

    }

    // A helper method to help determine if the file has a valid format
    private boolean isValidFormat(File f, List<String> format) {
        for (String form : format) {
            if (f.getName().endsWith(form)) {
                return true;
            }
        }
        return false;
    }

    private void putBlockLocation(int id, int x, int y) {
        ArrayList<Integer> location = new ArrayList<>();
        location.add(x);
        location.add(y);
        blockLocations.put(id, location);
    }

    /**
     * Sets up the game thread text area with custom styling.
     */
    private void setUpGameThreadTextArea() {
        // Code to set up gameThreadTextArea and its scrollPane
        JTextArea gameThreadTextArea = new JTextArea();

        // Set the JTextArea in OutputPresenter
        OutputPresenter.setGameThreadTextArea(gameThreadTextArea);

        gameThreadTextArea.setFont(new Font(DEFAULT_FONT,
                Font.PLAIN,
                DEFAULT_FONT_SIZE));
        gameThreadTextArea.setForeground(Color.WHITE);
        gameThreadTextArea.setBounds(25,
                684,
                SIDEBAR_WIDTH - 35,
                THREAD_SECTION_HEIGHT + 75);
        gameThreadTextArea.setEditable(false);
        gameThreadTextArea.setLineWrap(true);

        // Set the opaque property to false
        gameThreadTextArea.setOpaque(false);

        // Transparent background
        Color transparentColor = new Color(0, 0, 0, 0);
        gameThreadTextArea.setBackground(transparentColor);

        JScrollPane scrollPane = new JScrollPane(gameThreadTextArea);
        scrollPane.setBounds(25,
                684,
                SIDEBAR_WIDTH - 35,
                THREAD_SECTION_HEIGHT + 75);

        // Make the scroll pane non-opaque
        scrollPane.setOpaque(false);

        // Make the viewport non-opaque
        scrollPane.getViewport().setOpaque(false);

        // Remove the border
        scrollPane.setBorder(null);

        // Set viewport background to transparent
        scrollPane.getViewport().setBackground(transparentColor);

        add(scrollPane);
    }

    /**
     * Initializes and configures the JTextAreas for each player.
     */
    private void setUpPlayerTextAreas() {
        // Code to initialize and configure the JTextAreas for each player
        List<JTextArea> playerTextAreas = new ArrayList<>();

        // Initialize and configure the JTextAreas for each player
        for (int i = 0; i < 4; i++) {
            JTextArea playerTextArea = new JTextArea();
            playerTextArea.setFont(new Font(DEFAULT_FONT, Font.BOLD, DEFAULT_FONT_SIZE));
            playerTextArea.setEditable(false);
            playerTextArea.setLineWrap(true);
            playerTextArea.setBounds(40, 80 + i * 160, 160, 60); // Adjust positions as needed
            add(playerTextArea);
            playerTextAreas.add(playerTextArea);
        }
        PlayerInfoPanel.setallplayerTextAreas(playerTextAreas);

        // Create a new panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Set the FlowLayout to align to the right
        buttonPanel.setBounds(getWidth() - 200, 10, 180, 40); // Increase the width
    }

    /**
     * Draws an image on the Graphics context at specified coordinates.
     */
    public void drawImage(Graphics g, BufferedImage image, int x, int y) {
        g.drawImage(image, x, y, this); // Draw the image at the specified coordinates
    }


    /**
     * Draws blocks on the Graphics context.
     * at the same time fill in a hash map for later application
     */
    private void drawBlocks(Graphics g) {
        putBlockLocation(STARTING_BLOCK_ID, 888, 13);
        drawImage(g, blocks.get(String.format("%d_go", STARTING_BLOCK_ID)), 888, 13);


        // Top right
        putBlockLocation(STARTING_BLOCK_ID + 1, 960, 13);
        drawImage(g, blocks.get(String.format("%d_0_0", STARTING_BLOCK_ID + 1)),
                960, 13);

        // Right
        for (int i = STARTING_BLOCK_ID + 2; i <= STARTING_BLOCK_ID + 12; i++) {
            drawImage(g, blocks.get(String.format("%d_0_0", i)),
                    960, 90 + (i - 102) * 80);
            putBlockLocation(i, 960, 90 + (i - 102) * 80);
        }

        // Bottom right
        putBlockLocation(STARTING_BLOCK_ID + 13, 960, 90 + (113 - 102) * 80);
        drawImage(g, blocks.get(String.format("%d_0_0", STARTING_BLOCK_ID + 13)),
                960, 90 + (112 - 102) * 80);

        // Bottom Middle
        putBlockLocation(STARTING_BLOCK_ID + 14, 888, 90 + (112 - 102) * 80);
        drawImage(g, blocks.get(String.format("%d_examcentre", STARTING_BLOCK_ID + 14)),
                888, 90 + (112 - 102) * 80);

        // Bottom left
        putBlockLocation(STARTING_BLOCK_ID + 15, 438, 90 + (112 - 102) * 80);
        drawImage(g, blocks.get(String.format("%d_0_0", STARTING_BLOCK_ID + 15)),
                438, 90 + (112 - 102) * 80);

        // Left
        for (int i = STARTING_BLOCK_ID + 26; i >= STARTING_BLOCK_ID + 16; i--) {
            putBlockLocation(i, 438, 90 + (126 - i) * 80);
            drawImage(g, blocks.get(String.format("%d_0_0", i)),
                    438, 90 + (126 - i) * 80);

        }

        // Top left
        drawImage(g, blocks.get(String.format("%d_0_0", STARTING_BLOCK_ID + BLOCKS_COUNT)),
                438, 13);
        putBlockLocation(STARTING_BLOCK_ID + BLOCKS_COUNT, 438, 13);

        // Special Images
        drawImage(g, blocks.get(String.format("%d_ttc", STARTING_BLOCK_ID + 6)),
                960, 90 + (106 - 102) * 80);
        drawImage(g, blocks.get(String.format("%d_destiny1", STARTING_BLOCK_ID + 10)),
                960, 90 + (110 - 102) * 80);
        drawImage(g, blocks.get(String.format("%d_destiny2", STARTING_BLOCK_ID + 18)),
                438, 90 + (126 - 118) * 80);
        drawImage(g, blocks.get(String.format("%d_destiny3", STARTING_BLOCK_ID + 25)),
                438, 90 + (126 - 125) * 80);
    }


    /**
     * Draws the game thread title on the Graphics context.
     */
    private void drawPlayers(Graphics g) {
        for (int i = 1; i <= 4; i++) {
            ArrayList<Integer> Location = playerPosition.get(i);
            drawImage(g, blocks.get("player" + i), Location.get(0), Location.get(1));
        }
    }

    private void drawInteractivePanel(Graphics g) {
        drawImage(g, interactivePanelImage, 1540, 540);
    }

    private void drawPlayersInfoPanel(Graphics g) {
       for (int i = 1; i <= 4; i++) {
       drawImage(g, blocks.get("player" + i + "_information_panel"), 0, 17 + (i - 1) * 160);
        }
    }

    public void blockReplace(int blockId, int ownerId, int nextLevel) {
        ownerId += 1;
        // Modify the GameBoard instance as needed
        blocks.replace(blockId + "_0_0", images.get(blockId + "_" + ownerId + "_" + nextLevel));
        repaint();
    }

    public void playerMove(int playerId, int nextBlockId) {
        playerId += 1;
        playerPosition.replace(playerId, playerLocation.get(playerId).get(nextBlockId));
    }

    public void rollDice(int playerId, int value) {
        playerId += 1;

        interactivePanelImage = images.get("p" + playerId + "_roll");
        repaint();
        JOptionPane.showMessageDialog(this,
                "Roll Dice",
                "Roll Dice",
                JOptionPane.INFORMATION_MESSAGE);

        interactivePanelImage = images.get("p" + playerId + "_" + value);
        repaint();
    }

    public void bankrupt(int playerId) {
        playerId += 1;

        blocks.remove("player" + playerId);
        blocks.replace("player" + playerId + "_information_panel", images.get("bankruptcy"));

        repaint();

    }

    public void updateAll(GameData data) {
        // Update player information
        for (Player player : data.currentPlayers) {
            if (!StatusChecker.isPlayable(player)) {
                this.bankrupt(player.getUserId());
                PlayerInfoPanel.clearPlayerPanel(player.getUserId());
            }
            else {
                PlayerInfoPanel.updatePanel(player.getUserId(), player.getMoney());
                this.playerMove(player.getUserId(), player.getPosition());
            }
        }
        // Update block information
        for (Block block : data.blocks) {
            if (!(block instanceof Property))
                continue;

            int ownerId = -1;

            Player owner = ((Property) block).getOwner();
            if (owner != null)
                ownerId = owner.getUserId();
            this.blockReplace(block.getId(), ownerId, ((Property) block).getLevel());
        }
    }
}
