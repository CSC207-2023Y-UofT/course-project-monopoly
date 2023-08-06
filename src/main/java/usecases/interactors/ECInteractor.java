package usecases.interactors;

import entities.Block;
import entities.GameData;
import entities.UseCaseInteractor;
import usecases.impactors.StatusImpactor;

/**
 * The ECInteractor class represents an interactor that handles the exam center interaction in the game.
 * It implements the UseCaseInteractor interface to provide a method for interacting with the exam center when a player steps on it.
 */
public class ECInteractor implements UseCaseInteractor {

    /**
     * Interacts with the exam center when a player steps on it.
     * The player will be stopped from moving for 2 rounds.
     *
     * @param block The exam center block on which the player stepped.
     * @param data  The game data containing the current state of the game.
     */
    @Override
    public void interact(Block block, GameData data) {
        // Player will be stopped for 2 rounds
        StatusImpactor.changeStatus(data.currentPlayer,"movable",-2);
        System.out.println("Player " + data.currentPlayer.getUserId() + " is in the exam center");
        // UI: Update the current player's movable status to show they are in the exam center
    }
}
