package presenters;

import javax.swing.*;
import java.util.List;


/**
 * This presenter class contain methods that modify the Player Information Panel.
 */
public class PlayerInfoPanel {
    private static List<JTextArea> allplayerTextAreas;

    public static void setallplayerTextAreas(List<JTextArea> playerTextAreass) {
        allplayerTextAreas = playerTextAreass;
    }

    /**
     * Update player information in Player Information Panel
     * as the result of a change of saving for player (playerId).
     *
     * @param playerId eg. 1
     * @param currSaving the saving of the player after the change.
     */
    public static void updatePanel(int playerId, int currSaving) {
        System.out.println("[INFO PANEL] Current saving for Player " + playerId + ": " + currSaving + " TBucks. ");

        if (!(allplayerTextAreas == null))
            allplayerTextAreas.get(playerId - 1).setText(currSaving + " TBucks");
    }

}