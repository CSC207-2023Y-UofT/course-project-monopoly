package useCases.interactor;

import entity.*;
import useCases.*;

public class PPTInteractor implements UseCaseInteractor {
    @Override
    public void interact(Block block, GameData data) {
        // player is owner
        if (OwnerIdentifier.isOwner(data.currentPlayer, (Property) block)) {
            OwnerPropertyUseCase.run(data.currentPlayer, (Property) block);
        }
        // player is not owner
        else {
            PasserbyUseCase.PasserbyArrival(data.currentPlayer, (Property) block, ((Property) block).getOwner());
            System.out.println("Player" + data.currentPlayer.getUserId() + " passed by property "
                    + block.getId() + " and paid tax, current tbucks " + data.currentPlayer.getMoney());
        }
    }
}
