package presenters;

import java.util.HashMap;
import java.util.Map;

/**
 * This presenter class contain methods that modify the Player Information Panel.
 * NOTE: Since we are at the terminal version, this class is minimally implemented.
 */
public class PlayerInfoPanel {
    private static final Map<Integer, String> infoPanelMap = new HashMap<>();

    static {
        infoPanelMap.put(1, "1");
        infoPanelMap.put(2, "2");
        infoPanelMap.put(3, "3");
        infoPanelMap.put(4, "4");
    }
    public static void updatePanel(int playerId, int currSaving) {
        System.out.println("[INFO PANEL] Current saving for Player " +
                infoPanelMap.get(playerId) + ": " + currSaving + " TBucks. ");
    }
}
