package presenters;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;


//        HashMap<String, BufferedImage> images = gameBoard.getImages();
//        HashMap<Integer, ArrayList<Integer>> blockLocations = gameBoard.getBlockLocations();

import java.util.ArrayList;


public class GameMapPanel {
    private GameBoard gameBoard;

    public GameMapPanel(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    // Import the GameBoard class

    public static void GameMapModifier() {
        System.out.println("[MAP] The game map has been updated.");
    }

    public static void blockReplace(GameBoard gameBoard, int blockId, int ownerId, int currLevel, int nextLevel) {
        // Modify the GameBoard instance as needed
    }

    public static void playerMove(GameBoard gameBoard, int blockId, int playerId, int currBlockId, int nextBlockId) {
        // Modify the GameBoard instance as needed
    }

    public static void drawNewImageOnBoard(GameBoard gameBoard, String imageName, int x, int y) {
    }
}