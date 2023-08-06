package useCases;

import entity.DestinyCard;
import entity.GameData;
import entity.Player;
import useCases.impactor.MoneyImpactor;
import useCases.impactor.PositionImpactor;
import useCases.impactor.StatusImpactor;

import java.util.ArrayList;

/**
 * The DestinyCardExecutor is responsible for executing the actions of a destiny card.
 * It applies the effects of the destiny card on the game data and the player.
 */
public class DestinyCardExecutor {

    /**
     * Executes the actions of a given destiny card on the game data and the player.
     *
     * @param data   The game data containing information about the game state.
     * @param player The player on whom the destiny card's actions will be applied.
     * @param card   The destiny card whose actions will be executed.
     * @return The message associated with the destiny card after execution.
     */
    public static String executeCard(GameData data, Player player, DestinyCard card){
        try {
            if (data == null || player == null || card == null) {
                throw new NullPointerException("Data, player, and card must not be null.");
            }
            ArrayList<Object> actions = card.getActions();

            if (actions.size() != 3) {
                throw new IllegalArgumentException("Invalid action data in the destiny card.");
            }

            for(Object action: actions){
                if (! (action instanceof Integer)){
                    throw new IllegalArgumentException("Invalid action data in the destiny card.");
                }
            }

            int moneyChange = -((int) actions.get(0));
            if (moneyChange != 0) {
                MoneyImpactor.deduct(moneyChange, player);
            }

            int position = (int) actions.get(1);

            if (position >= 100) {
                PositionImpactor.absoluteMove(data, position);
            } else if (position != 0){
                boolean isPassStartingPoint = PositionImpactor.relativeMove(data, position);
                if (isPassStartingPoint) StartingPointUseCase.giveBonus(player);
            }

            int statusChange = (int) actions.get(2);
            if (statusChange != 0) {
                StatusImpactor.changeStatus(player, "movable", statusChange);
            }

            return card.getMessage();
        } catch (NullPointerException e) {
            // Handle NullPointerException (data, player, or card is null)
            e.printStackTrace();
            System.err.println("Data, player, and card must not be null.");
            return "Choose card again";
        } catch (IllegalArgumentException e) {
            // Handle IllegalArgumentException (invalid action data in destiny card)
            System.err.println("Invalid action data in the destiny card.");
            return "Choose card again";
        }
    }
}
