package usecases.interactors;

import entities.*;
import usecases.*;
import presenters.*;

/**
 * The DestinyInteractor class represents an interactor that handles the destiny card interaction in the game.
 * It implements the UseCaseInteractor interface to provide a method for interacting with the destiny card when a player steps on it.
 */
public class DestinyInteractor implements UseCaseInteractor {

    /**
     * Interacts with the destiny card when a player steps on it.
     *
     * @param block The destiny block on which the player stepped.
     * @param data  The game data containing the current state of the game.
     */
    @Override
    public void interact(Block block, GameData data) {
        DestinyCard card = DestinyCardChooser.chooseCard((Destiny) block);
//        System.out.println(DestinyCardExecutor.executeCard(data, data.currentPlayer, card));
        InputPresenter.detiny(data.currentPlayer, card.getMessage());
        String message = DestinyCardExecutor.executeCard(data, data.currentPlayer, card);
        OutputPresenter.notifyDestiny(message);
        // UI: Display the result message on the game board
    }
}
