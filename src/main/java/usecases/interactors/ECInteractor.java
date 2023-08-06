package usecases.interactors;

import entities.Block;
import entities.GameData;
import entities.UseCaseInteractor;
import usecases.impactors.StatusImpactor;

public class ECInteractor implements UseCaseInteractor {
    @Override
    public void interact(Block block, GameData data) {
        /**
         *  when player step on exam center
         * @param  Block block
         * @param  GameData data
         */
        // player will be stopped for 2 rounds
        StatusImpactor.changeStatus(data.currentPlayer,"movable",-2);
        System.out.println("Player " + data.currentPlayer.getUserId() + " is in the exam center");
        //UI: update current player movable status
    }
}
