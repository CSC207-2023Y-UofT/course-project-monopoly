package presenters;

/**
 * This presenter class contain methods that output game progress information
 * in the Game Progress Panel.
 * NOTE: Since we are at the terminal version, this class is minimally implemented.
 */
public class OutputPresenter {

    /**
     * Print a message at the terminal
     * which announces that a player has invested / upgraded a property.
     */
    public static void notifyOwnerUpgraded(int playerId, String verb,
                                           String propName, int currPrice, int level) {
        System.out.printf("Player %d %s %s for %d TBucks.%n",
                playerId, verb, propName, currPrice);
        System.out.printf("%s is now at level %d.%n", propName, level);
    }

    /**
     * Print a message at the terminal
     * which announces that a player has ignored a property.
     */
    public static void notifyOwnerIgnored(int playerId, String propName) {
        System.out.printf("Player %d ignored %s.%n", playerId, propName);
    }

    /**
     * Print a message at the terminal
     * which announces that a player has been charged at a property owned by others.
     */
    public static void notifyPasserbyPaid(int passerId, int tax,
                                          int ownerId, String propName) {
        System.out.printf("Player %d paid %d to Player %d at %s.%n",
                passerId, tax, ownerId, propName);
    }

    /**
     * Print a message at the terminal
     * which informs that a property has reached max level.
     */
    public static void notifyMaxLevel(String propName) {
        System.out.println(propName + " has reached maximum level (3). ");
    }

    /**
     * Print a message at the terminal
     * which informs that the player does not have enough TBucks for his/her activity.
     */
    public static void notifyInsufficientFund() {
        System.out.println("Insufficient Fund :(");
    }

}
