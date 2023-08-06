package usecases;

import entities.Player;
import entities.StartingPoint;
import usecases.impactors.MoneyImpactor;

/**
 * The StartingPointUseCase class represents a use case when the player passes through the starting point.
 * It provides a method to give a bonus to the player when they pass the starting point.
 */
public class StartingPointUseCase {

    /**
     * Gives a bonus to the player when they pass the starting point.
     *
     * @param player The player to whom the bonus should be given.
     */
    public static void giveBonus(Player player){
        int bonus = StartingPoint.getBonus();
        MoneyImpactor.deduct(bonus * -1, player);
    }
}
