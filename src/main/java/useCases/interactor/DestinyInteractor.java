package useCases.interactor;

import entity.*;
import useCases.*;

public class DestinyInteractor implements UseCaseInteractor {
    @Override
    public void interact(Block block, GameData data) {
        DestinyCard card = DestinyCardChooser.chooseCard((Destiny) block);
        System.out.println(DestinyCardExecutor.executeCard(data, data.currentPlayer, card));
        // UI: display message
    }
}
