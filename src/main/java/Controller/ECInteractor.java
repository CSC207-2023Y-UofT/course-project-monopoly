package controller;

import entity.Block;
import entity.GameData;
import entity.UseCaseInteractor;
import useCases.impactor.StatusImpactor;

public class ECInteractor implements UseCaseInteractor {
    @Override
    public void interact(Block block, GameData data) {
        // player will be stopped for 2 rounds
        StatusImpactor.changeStatus(data.currentPlayer,"movable",-2);
        System.out.println("Player " + data.currentPlayer.getUserId() + " is in the exam center");
        //UI: update current player movable status
    }
}
