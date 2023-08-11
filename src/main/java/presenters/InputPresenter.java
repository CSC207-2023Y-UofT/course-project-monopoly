package presenters;

import controllers.InteractivePanelAdapter;
import entities.Player;

import java.util.Scanner;

/**
 * This presenter class contain methods that collect raw user input from the
 * Main Interactive Panel
 * NOTE: Since we are at the terminal version, this class is minimally implemented.
 */
public class InputPresenter {

    /**
     * Asks for and collect raw input from the user
     * when player chooses whether to invest / upgrade or ignore a property, if available.
     */
    public static boolean ownerChooseAtProperty(String verb, String propName, int currPrice) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.printf("%s %s for %d TBucks? (Y/N)%n", verb, propName, currPrice);
            String choiceIndicator = scanner.nextLine();
            int choice = InteractivePanelAdapter.ownerChooseAtProperty(choiceIndicator);

            if (choice == 1) {
                return true;
            } else if (choice == 0) {
                return false;
            } else {
                System.out.print("Invalid Input! Please try again.\n");
            }
        }
    }

    public static int playerChooseBlock() {
        return 0;
    }
    public static void notifyWinner(int playerId)
    {}
    public static void notifyWinner()
    {}
    public static void detiny(Player currentPlayer, String message) {}
}
