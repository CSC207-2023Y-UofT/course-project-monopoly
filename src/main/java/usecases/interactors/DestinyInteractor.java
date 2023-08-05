package usecases.interactors;

import entities.*;
import usecases.*;

public class DestinyInteractor implements UseCaseInteractor {
    @Override
    public void interact(Block block, GameData data) {
        /**
         *  when player step on destiny
         * @param  Block block
         * @param  GameData data
         */
        DestinyCard card = DestinyCardChooser.chooseCard((Destiny) block);
        System.out.println(DestinyCardExecutor.executeCard(data, data.currentPlayer, card));
        // UI: display message
    }
}
