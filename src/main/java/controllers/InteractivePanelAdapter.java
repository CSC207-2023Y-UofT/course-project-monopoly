package controllers;

/**
 * This intermediate adapter class contain methods that collect raw user input
 * from InputPresenter and process them into simpler forms.
 * NOTE: Since we are at the terminal version, this class is minimally implemented.
 */
public class InteractivePanelAdapter {

    /**
     * Processes the input of player as he/she chooses to invest/upgrade
     * at an unoccupied property or a property owned by him/her.
     * @param choiceIndicator raw input from InputPresenter.OwnerChooseAtProperty
     */
    public static int ownerChooseAtProperty(String choiceIndicator) {
        if (choiceIndicator.equals("Y") || choiceIndicator.equals("y")) {
            return 1;
        } else if (choiceIndicator.equals("N") || choiceIndicator.equals("n")) {
            return 0;
        } else {
            System.out.println("[!] Invalid input, please try again. ");
            return 2;
        }
    }
}
