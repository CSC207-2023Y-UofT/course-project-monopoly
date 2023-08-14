package presenters;

import entities.GameData;
import usecases.InputPresentingInferface;

import javax.swing.*;
import java.util.Scanner;

/**
 * This is the legacy terminal version of getting user input. Mainly used for testing purpose
 */
public class TerminalInputPresenter implements InputPresentingInferface {
    /**
     * Asks for and collect raw input from the user
     * when player chooses whether to invest / upgrade or ignore a property, if available.
     */
    @Override
    public boolean ownerChooseAtProperty(String verb, String propName, int currPrice) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.printf("%s %s for %d TBucks? (Y/N)%n", verb, propName, currPrice);
            String choice = scanner.nextLine();

            if (choice.equals("Y") || choice.equals("y")) {
                return true;
            } else if (choice.equals("N")|| choice.equals("n")) {
                return false;
            }
        }
    }

    @Override
    public int playerChooseBlock(GameData data) {
        Scanner scanner = new Scanner(System.in);
        int blockID;
        String userInput;
        while (true) {
            try {
                System.out.println("Enter a block to go to: ");
                userInput = scanner.nextLine();
                blockID = 100 + Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid block id.");
                scanner.next();
                continue;
            }
            if (blockID - 100 < 0 || blockID - 100 > data.blocks.size() ||
                    data.getBlockFromId(blockID).getBlockName().equals("ttcstation") ) {
                System.out.println("Please enter valid block id.");
                continue;
            }
            break;
        }
        return blockID;
    }
}
