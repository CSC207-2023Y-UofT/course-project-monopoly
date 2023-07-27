package usecase;

import entity.Player;
import entity.StartingPoint;
import usecase.impactor.MoneyImpactor;

/**
 * Represent the use case when the player passing through the starting point
 */
public class StartingPointUseCase {
    public static void giveBonus(Player player, StartingPoint startingPoint){
        int bonus = startingPoint.getBonus();
        MoneyImpactor.deduct(bonus * -1, player);
    }
}
