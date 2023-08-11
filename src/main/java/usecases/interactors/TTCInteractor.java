package usecases.interactors;

import entities.*;
import presenters.InputPresenter;
import usecases.impactors.PositionImpactor;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The TTCInteractor class represents an interactor that handles the TTC station interaction in the game.
 * It implements the UseCaseInteractor interface to provide a method for interacting with the TTC station when a player steps on it.
 */
public class TTCInteractor implements UseCaseInteractor {

    /**
     * Interacts with the TTC station when a player steps on it.
     * The player will be prompted to enter a block ID to move to using the TTC station.
     * After the move, the corresponding block's run method is executed.
     *
     * @param block The TTC station block on which the player stepped.
     * @param data  The game data containing the current state of the game.
     */
    @Override
    public void interact(Block block, GameData data) {
        System.out.println("Enter a block id: ");
        Scanner scanner = new Scanner(System.in);
        int blockID;
        blockID = InputPresenter.playerChooseBlock(data);

        // Move the player to the chosen block using absoluteMove
        PositionImpactor.absoluteMove(data,blockID);

        // UI: Display the player's move using absoluteMove
        System.out.println("Player " + data.currentPlayer.getUserId() + " moved to " + data.currentPlayer.getPosition());

        // Execute the run method of the new block the player moved to
        Block newBlock = data.getBlockFromId(blockID);
        newBlock.run(data);

    }
}
