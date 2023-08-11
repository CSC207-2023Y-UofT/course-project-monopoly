package presenters;
import entities.Block;
import entities.GameData;
import entities.Player;
import entities.Property;
import usecases.StatusChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * GameBoard class for managing and rendering the game GUI.
 * This class extends JFrame and handles the main game board display, background images, player text areas,
 * and game-related drawings.
 */
public class GameBoard extends JFrame {
    private boolean keyPressed = false; // Instance variable
    private static boolean buttonResult = false;

    public HashMap<String, BufferedImage> getImages() {
        return images;
    }

    public HashMap<Integer, ArrayList<Integer>> getBlockLocations() {
        return blocklocations;
    }

    private BufferedImage backgroundImage;
    private BufferedImage interactivePanelImage;
    private static final int SIDEBAR_WIDTH = 400;
    private static final int THREAD_SECTION_HEIGHT = 280;  // bottom half of the sidebar

    private List<JTextArea> playerTextAreas;

    // The first Hash Map: For file name : image
    public HashMap<String, BufferedImage> images;
    public HashMap<String, BufferedImage> blocks;

    // Second Hash Map: For Block ID : Location
    private HashMap<Integer, ArrayList<Integer>> blocklocations;


    // PlayerID: Hashmap<BlockID: Location>
    private HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> playerLocation;

    private HashMap<Integer, ArrayList<Integer>> playerPosition;

    private Boolean playerDecision = null;
    //interactive panels
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
        loadBackgroundImage();

        // Set up the main frame
//        setUndecorated(true); // Remove window decorations
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // two hash maps
        images = new HashMap<String, BufferedImage>();
        blocklocations = new HashMap<>();

        //initialize player location.
        playerLocation = new HashMap<>();
        for (int i = 1; i <= 4; i++){
            int startX = 0;
            int startY = 0;
            //initialize starting position
            if (i == 1){
                startX = 928;
                startY = 93;
            }
            if (i == 2){
                startX = 960;
                startY = 93;
            }
            if (i == 3){
                startX = 928;
                startY = 125;
            }
            if (i == 4){
                startX = 960;
                startY = 125;
            }

            HashMap<Integer, ArrayList<Integer>> temp_hash = new HashMap<>();

            for (int j = 100; j <= 127; j++){
                if (j == 100){
                    ArrayList<Integer> location = new ArrayList<>(Arrays.asList(startX, startY));
                    temp_hash.put(j, location);
                    startX += 90;
                }

                if (j == 101){
                    ArrayList<Integer> location = new ArrayList<>(Arrays.asList(startX, startY));
                    temp_hash.put(j, location);
                    startX += 94;
                }

                if (j >= 102 && j <= 112){
                    ArrayList<Integer> location = new ArrayList<>(Arrays.asList(startX, startY));
                    temp_hash.put(j, location);
                    startY += 80;
                }

                if (j == 113){
                    startY -= 65;
                    startX -= 94;
                    ArrayList<Integer> location = new ArrayList<>(Arrays.asList(startX, startY));
                    temp_hash.put(j, location);
                }

                if (j == 114){
                    startX -= 90;
                    ArrayList<Integer> location = new ArrayList<>(Arrays.asList(startX, startY));
                    temp_hash.put(j, location);
                }

                if (j == 115){
                    startX -= 90;
                    ArrayList<Integer> location = new ArrayList<>(Arrays.asList(startX, startY));
                    temp_hash.put(j, location);
                    startX -= 94;
                    startY -= 10;
                }

                if (j >= 116 && j <= 126){
                    ArrayList<Integer> location = new ArrayList<>(Arrays.asList(startX, startY));
                    temp_hash.put(j, location);
                    startY -= 80;
                }

                if (j == 127){
                    startY += 78;
                    startX += 94;
                    ArrayList<Integer> location = new ArrayList<>(Arrays.asList(startX, startY));
                    temp_hash.put(j, location);
                }

                playerLocation.put(i, temp_hash);
            }
        }

        //initialize player Position
        playerPosition = new HashMap<>();
        for (int i = 1; i <= 4; i++){
            playerPosition.put(i, playerLocation.get(i).get(100));
        }


        File folder = new File("data/images/blocks (test)"); // Replace with your directory path
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && (file.getName().endsWith(".png") || file.getName().endsWith(".jpg"))) { // Add more extensions if needed
                    try {
                        BufferedImage img = ImageIO.read(file);
                        String nameWithoutExtension = file.getName().substring(0, file.getName().lastIndexOf('.'));
                        images.put(nameWithoutExtension, img);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        File folderPlayers = new File("data/images/players"); // Replace with your directory path
        File[] listOfFilesplayers = folderPlayers.listFiles();

        if (listOfFilesplayers != null) {
            for (File file : listOfFilesplayers) {
                if (file.isFile() && (file.getName().endsWith(".png") || file.getName().endsWith(".jpg"))) { // Add more extensions if needed
                    try {
                        BufferedImage img = ImageIO.read(file);
                        String nameWithoutExtension = file.getName().substring(0, file.getName().lastIndexOf('.'));
                        images.put(nameWithoutExtension, img);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }



        File folderRolls = new File("data/images/interactive"); // Replace with your directory path
        File[] listOfFileRolls = folderRolls.listFiles();

        if (listOfFileRolls != null) {
            for (File file : listOfFileRolls) {
                if (file.isFile() && (file.getName().endsWith(".png") || file.getName().endsWith(".jpg"))) { // Add more extensions if needed
                    try {
                        BufferedImage img = ImageIO.read(file);
                        String nameWithoutExtension = file.getName().substring(0, file.getName().lastIndexOf('.'));
                        images.put(nameWithoutExtension, img);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        interactivePanelImage = images.get("p1_roll");

        File folderInteractive = new File("data/images/infopanel"); // Replace with your directory path
        File[] listOfFileInteractive = folderInteractive.listFiles();

        if (listOfFileInteractive != null) {
            for (File file : listOfFileInteractive) {
                if (file.isFile() && (file.getName().endsWith(".png") || file.getName().endsWith(".jpg"))) { // Add more extensions if needed
                    try {
                        BufferedImage img = ImageIO.read(file);
                        String nameWithoutExtension = file.getName().substring(0, file.getName().lastIndexOf('.'));
                        images.put(nameWithoutExtension, img);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        blocks = images;


        // Paint the board
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
//              drawGameTitle(g);
                drawBlocks(g);
                drawPlayers(g);
                drawInteractivePanel(g);
                drawPlayersInfoPanel(g);
            }
        };

        setUpPlayerTextAreas();
        setContentPane(panel);

        // Add the Text Area
        setLayout(null);
        setUpGameThreadTextArea();


        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'Y' || e.getKeyChar() == 'y') {
                    playerDecision = true;
                } else if (e.getKeyChar() == 'N' || e.getKeyChar() == 'n') {
                    playerDecision = false;
                }
            }
        });
        this.setFocusable(true); // Ensure the JFrame can receive key events
    }

    // Method to fetch the player's decision
    public Boolean getPlayerDecision() {
        return playerDecision;
    }

    // Method to reset player's decision
    public void resetPlayerDecision() {
        playerDecision = null;
    }


    private void putBlockLocation(int id, int x, int y) {
        ArrayList<Integer> location = new ArrayList<>();
        location.add(x);
        location.add(y);
        blocklocations.put(id, location);
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

        gameThreadTextArea.setFont(new Font("Arial", Font.PLAIN, 20));
        gameThreadTextArea.setForeground(Color.WHITE);
        gameThreadTextArea.setBounds(25, 684, SIDEBAR_WIDTH - 35, THREAD_SECTION_HEIGHT + 75);
        gameThreadTextArea.setEditable(false);
        gameThreadTextArea.setLineWrap(true);
        gameThreadTextArea.setOpaque(false); // Set the opaque property to false
        gameThreadTextArea.setBackground(new Color(0, 0, 0, 0)); // Transparent background

        JScrollPane scrollPane = new JScrollPane(gameThreadTextArea);
        scrollPane.setBounds(25, 684, SIDEBAR_WIDTH - 35, THREAD_SECTION_HEIGHT + 75);
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

        //at the same time create
        // Draw the blocks

        putBlockLocation(100, 888, 13);
        drawImage(g, blocks.get("100_go"), 888, 13);


        // Top right
        putBlockLocation(101, 960, 13);
        drawImage(g, blocks.get("101_0"), 960, 13);

//      //Right side
        for (int i = 102; i <= 112; i ++) {
            drawImage(g, blocks.get(i + "_0"), 960, 90 + (i - 102) * 80);
            putBlockLocation(i, 960, 90 + (i - 102) * 80);
        }

        //Bottom right
        drawImage(g, blocks.get("113_0"), 960, 90 + (112 - 102) * 80);
        putBlockLocation(113, 960, 90 + (113 - 102) * 80);

        //Bottom Middle
        drawImage(g, blocks.get("114_ex"), 888, 90 + (112 - 102) * 80);
        putBlockLocation(114, 888, 90 + (112 - 102) * 80);

        //Bottom left
        drawImage(g, blocks.get("115_0"), 438, 90 + (112 - 102) * 80);
        putBlockLocation(114, 438, 90 + (112 - 102) * 80);


        //Left side
        for (int i = 126; i >= 116; i --) {
            drawImage(g, images.get(i + "_0"), 438, 90 + (126 - i) * 80);
            putBlockLocation(114, 438, 90 + (126 - i) * 80);
        }

        //Top left
        drawImage(g, images.get("127_0"), 438, 13);
        putBlockLocation(127, 438, 13);

        //special ones
        drawImage(g, images.get("106_ttc"), 960, 90 + (106 - 102) * 80);
        drawImage(g, images.get("110_des"), 960, 90 + (110 - 102) * 80);
        drawImage(g, images.get("118_des"), 438, 90 + (126 - 118) * 80);
        drawImage(g, images.get("125_des"), 438, 90 + (126 - 125) * 80);

    }


    /**
     * Draws the game thread title on the Graphics context.
     */
    private void drawPlayers(Graphics g){
        for (int i = 1; i <= 4; i++){
            ArrayList<Integer> Location = playerPosition.get(i);
            drawImage(g, blocks.get("player" + i), Location.get(0) , Location.get(1));
        }
    }

    private void drawInteractivePanel(Graphics g){
        drawImage(g, interactivePanelImage, 1540, 540);
    }

    private void drawPlayersInfoPanel(Graphics g){
        for (int i = 1; i <= 4; i++){
            drawImage(g, blocks.get("player" + i + "_information_panel"),0, 17 + (i-1) * 160);
        }
    }


    /**
     * Main method to run the GameBoard; Just for testing
     */
    private void addInteractivePanel() {
        // Interactive panel
        JPanel interactivePanel = new JPanel();
        interactivePanel.setOpaque(false);  // Set the panel to be transparent

        // Button1 - will set the result to True
        JButton button1 = new JButton("Yes");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonPress(true);
            }
        });

        // Button2 - will set the result to False
        JButton button2 = new JButton("No");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonPress(false);
            }
        });

        interactivePanel.add(button1);
        interactivePanel.add(button2);

        // Set panel's position and size
        interactivePanel.setBounds(1650, 270, 130, 80);// x, y, width, height

        // Add interactive panel to the JFrame
//        add(interactivePanel);
    }

    private void handleButtonPress(boolean result) {
        buttonResult = result;
        if (result) {
            System.out.println("Yes was pressed");
        } else {
            System.out.println("No was pressed");
        }
    }
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
            PlayerInfoPanel.updatePanel(3, 3000);
            PlayerInfoPanel.updatePanel(4, 3000);


            frame.bankrupt(1);
            frame.rollDice(1, 1);
            System.out.println(frame.getPlayerDecision());
        });
    }
    public void deletePlayer(int playerId)
    {}
    public void blockReplace(int blockId, int ownerId, int nextLevel) {
        ownerId += 1;
        // Modify the GameBoard instance as needed
//        images.replace(blockId + "_" + currLevel, images.get(blockId + "_" + nextLevel));
         blocklocations.get(blockId);
        blocks.replace(blockId + "_", images.get("105_0"));
        repaint();
    }

    public void playerMove(int playerId, int nextBlockId) {
        playerId += 1;
        playerPosition.replace(playerId, playerLocation.get(playerId).get(nextBlockId));
    }

    public void rollDice(int playerId, int value){
        interactivePanelImage = images.get("p" + playerId + "_roll");
        repaint();
        Boolean decision = getPlayerDecision();
        if (decision != null) {
            if (decision) {
                System.out.println("Yes");
                interactivePanelImage = images.get("p" + playerId + "_" + value);
                repaint();
            } else {
                // player pressed 'N'
            }
            resetPlayerDecision(); // reset after reading
        }
    }

    public void bankrupt(int playerId){
        playerId += 1;
        blocks.remove("player" + playerId);
        blocks.replace("player" + playerId + "_information_panel", images.get("bankruptcy"));
        repaint();

    }

    public void updateAll(GameData data) {
        // update player information
        for (Player player: data.currentPlayers) {
            if (!StatusChecker.isPlayable(player))
                this.deletePlayer(player.getUserId());
            else {
                PlayerInfoPanel.updatePanel(player.getUserId(), player.getMoney());
                this.playerMove(player.getUserId(), player.getPosition());
            }
        }
        // update block information
        for (Block block: data.blocks) {
            if (!(block instanceof Property))
                continue;
            this.blockReplace(block.getId(), ((Property) block).getOwner().getUserId(), ((Property) block).getLevel());
        }


    }
}








