package presenters;


/**
 * This presenter class contain methods that modify the Player Information Panel.
 * NOTE: Since we are at the terminal version, this class is minimally implemented.
 */
public class PlayerInfoPanel {

    public static void updatePanel(int playerId, int currSaving) {
        System.out.println("[INFO PANEL] Current saving for Player " + playerId + ": " + currSaving + " TBucks. ");
    }
}
