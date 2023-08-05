package usecases;
import entities.*;
import presenters.OutputPresenter;
import usecases.impactors.MoneyImpactor;

/**
 * Represents the use case when a player arrives at a property owned by others.
 */
public class PasserbyUseCase {

    /**
     * Proceeds with the event where a player arrives at a property owned by others.
     *
     * @param passer   The player who is passing by the block.
     * @param property The property on this block.
     * @param owner    The player who owns this property.
     */
    public static void passerbyArrival(Player passer, Property property, Player owner) {
        int passerId = passer.getUserId();
        int ownerId = owner.getUserId();

        String propName = property.getName();
        int tax = property.getTax();

        MoneyImpactor.trade(tax, passer, owner);

        OutputPresenter.notifyPasserbyPaid(passerId, tax, ownerId, propName);

    }
}


