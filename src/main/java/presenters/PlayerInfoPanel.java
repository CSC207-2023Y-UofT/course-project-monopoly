package presenters;

import javax.swing.*;
import java.util.List;


/**
 * This presenter class contain methods that modify the Player Information Panel.
 */
public class PlayerInfoPanel {
    private static List<JTextArea> allplayerTextAreas;

    public static void setallplayerTextAreas(List<JTextArea> playerTextAreas) {
        allplayerTextAreas = playerTextAreas;
    }

    /**
     * Update player information in Player Information Panel
     * as the result of a change of saving for player (playerId).
     *
     * @param playerId eg. (from 0 to 3)
     * @param currSaving the saving of the player after the change.
     */
    public static void updatePanel(int playerId, int currSaving) {
        playerId++;
        System.out.println("[INFO PANEL] Current saving for Player " + playerId + ": " + currSaving + " TBucks. ");

        if (!(allplayerTextAreas == null))
            allplayerTextAreas.get(playerId - 1).setText(currSaving + " TBucks");
    }

    public static void clearPlayerPanel(int playerId) {
        playerId++;
        if (!(allplayerTextAreas == null))
            allplayerTextAreas.get(playerId -1).setText("");
    }

}