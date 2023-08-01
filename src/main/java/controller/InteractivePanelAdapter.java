package controller;

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
    public static boolean ownerChooseAtProperty(String choiceIndicator) {
        if (choiceIndicator.equals("Y")) {
            return true;
        } else if (choiceIndicator.equals("N")) {
            return false;
        } else {
            System.out.println("Invalid input.");
            return false;
        }
    }
}
