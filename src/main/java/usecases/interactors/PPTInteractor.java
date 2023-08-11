package usecases.interactors;

import entities.*;
import usecases.*;

/**
 * The PPTInteractor class represents an interactor that handles the interaction with a property block in the game.
 * It implements the UseCaseInteractor interface to provide a method for interacting with a property block when a player steps on it.
 */
public class PPTInteractor implements UseCaseInteractor {

    /**
     * Interacts with a property block when a player steps on it.
     *
     * @param block The property block on which the player stepped.
     * @param data  The game data containing the current state of the game.
     */
    @Override
    public void interact(Block block, GameData data) {
        int id = data.currentPlayer.getUserId() + 1;
        // Check if the player is the owner of the property
        if (OwnerIdentifier.isOwner(data.currentPlayer, (Property) block)) {
            // Player is the owner, upgrade the property
            OwnerPropertyUseCase.ownerUpgrade(data.currentPlayer, (Property) block);
        }
        else {
            // Player is not the owner, interact with the property as a passerby
            PasserbyUseCase.passerbyArrival(data.currentPlayer, (Property) block,
                    ((Property) block).getOwner());
        }
    }
}
