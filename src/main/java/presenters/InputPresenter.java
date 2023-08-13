package presenters;

import controllers.InteractivePanelAdapter;
import entities.GameData;
import entities.Player;

import javax.swing.*;
import java.util.Scanner;

/**
 * This presenter class contain methods that collect raw user input from the
 * Main Interactive Panel
 * NOTE: Since we are at the terminal version, this class is minimally implemented.
 */
public class InputPresenter {

    private static GameBoard frame;
    public static void setFrame(GameBoard frame) {
        frame = frame;
    }

    /**
     * Asks for and collect raw input from the user
     * when player chooses whether to invest / upgrade or ignore a property, if available.
     */
    public static boolean ownerChooseAtProperty(String verb, String propName, int currPrice) {
        while (true) {
            int choice = JOptionPane.showConfirmDialog(frame,
                    String.format("%s %s for %d TBucks?", verb, propName, currPrice),
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                return true;
            } else if (choice == JOptionPane.NO_OPTION) {
                return false;
            }
        }
    }

    public static int playerChooseBlock(GameData data) {
        int blockID;
        String userInput;
        while (true) {
            try {
                userInput = JOptionPane.showInputDialog(frame, "Enter a block to go to: ");
                blockID = 100 + Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame,
                        "Please enter valid block id",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                continue;
            }
            if (blockID - 100 < 0 || blockID - 100 > data.blocks.size() ||
                    data.getBlockFromId(blockID).getBlockName().equals("ttcstation") ) {
                JOptionPane.showMessageDialog(frame,
                        "Please enter valid block id",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                continue;
            }
            break;
        }
        return blockID;
    }
    public static void notifyWinner(int playerId)
    {
        playerId += 1;
        JOptionPane.showMessageDialog(frame, "Winner is player " + playerId + "!", "Game over", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void notifyWinner()
    {
        JOptionPane.showMessageDialog(frame, "There is no winner",
                "Game over", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void detiny(Player currentPlayer, String message) {
        JOptionPane.showMessageDialog(frame,"Player " + currentPlayer.getUserId()+1 + " got a destiny card: \n" + message,
                "Destiny Card", JOptionPane.INFORMATION_MESSAGE);
    }
}
