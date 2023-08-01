package presenters;

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
    public static String ownerChooseAtProperty(String verb, String propName, int currPrice) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(verb + " " +  propName + " for " + currPrice + " TBucks? (Y/N)");
        String choiceIndicator = scanner.nextLine();

        return choiceIndicator;
    }
}
