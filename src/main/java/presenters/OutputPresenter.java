package presenters;

/**
 * This presenter class contain methods that output game progress information
 * in the Game Thread.
 * TODO
 */
public class OutputPresenter {
    private static int rounds = 1;

    /**
     * Print a message in the Game Thread
     * which says " :: Round (rounds) :: "
     */
    public static void notifyStartOfRound() {
        // TODO
        rounds ++ ;
    }

    /**
     * Print a message in the Game Thread
     * which says " [Player (playerId)'s Turn] "
     *
     * @param playerId eg. 1
     */
    public static void notifyTurn(int playerId) {
        // TODO
    }

    /**
     * Print a message in the Game Thread
     * which says " [Player (playerId) arrived at (blockName)] "
     *
     * @param playerId eg. 1
     * @param blockName eg. BA
     */
    public static void notifyMovement(int playerId, String blockName) {
        // TODO
    }

    /**
     * Print two messages in the Game Thread
     * The first one says "Player (playerId) (invested / upgraded) the property
     * named (propName) with (currPrice) T-Bucks. "
     * The second says "(propName) is now at level (level). "
     *
     * @param playerId eg. 1
     * @param verb eg. invested / upgraded
     * @param propName eg. BA
     * @param currPrice eg. 50
     * @param level eg. 3
     */
    public static void notifyOwnerUpgraded(int playerId, String verb,
                                           String propName, int currPrice, int level) {
        System.out.printf("Player %d %s %s for %d TBucks.%n",
                playerId, verb, propName, currPrice);
        System.out.printf("%s is now at level %d.%n", propName, level);
        // TODO
    }

    /**
     * Print a message in the Game Thread
     * which says "Player (playerId) ignored (propName). "
     *
     * @param playerId eg. 1
     * @param propName eg. BA
     */
    public static void notifyOwnerIgnored(int playerId, String propName) {
        System.out.printf("Player %d ignored %s.%n", playerId, propName);
        // TODO
    }

    /**
     * Print a message in the Game Thread
     * which says "Player (passerId) paid (tax) T-Bucks to Player (ownerId)
     * at (propName). "
     *
     * @param passerId eg. 2
     * @param tax eg. 150
     * @param ownerId eg. 1
     * @param propName eg. BA
     */
    public static void notifyPasserbyPaid(int passerId, int tax,
                                          int ownerId, String propName) {
        System.out.printf("Player %d paid %d TBucks to Player %d at %s.%n",
                passerId, tax, ownerId, propName);
        // TODO
    }

    /**
     * Print a message in the Game Thread
     * which says "(propName) has reached maximum level (5). "
     *
     * @param propName eg. BA
     */
    public static void notifyMaxLevel(String propName) {
        System.out.println(propName + " has reached maximum level (5). ");
        // TODO
    }

    /**
     * Print a message in the Game Thread
     * which says " Insufficient Fund :( ".
     */
    public static void notifyInsufficientFund() {
        System.out.println("Insufficient Fund :(");
        // TODO
    }

    /**
     * Print a message in the Game Thread
     * which says "Player (playerId) has passed GO block and received 200 T-Bucks. "
     *
     * @param playerId eg. 1
     */
    public static void notifyPassingGO(int playerId) {
        // TODO
    }

    /**
     * Print a message in the Game Thread
     * which says " :: Term tests has come to player (playerId) :: "
     *
     * @param playerId eg. 1
     */
    public static void notifyGoToExam(int playerId) {
        // TODO
    }

    /**
     * Print a message in the Game Thread
     * which says "Turns until movable for player (playerId): (remainTurns). "
     *
     * @param playerId eg. 1
     * @param remainTurns eg. 2
     */
    public static void notifyRemainingStopRounds(int playerId, int remainTurns) {
        // TODO
    }

    /**
     * Print a message in the Game Thread
     * which informs execution of a destiny card.
     *
     * @param message eg. ... // TODO
     */
    public static void notifyDestiny(String message) {
        // TODO
    }

}