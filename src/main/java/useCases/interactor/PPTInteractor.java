package useCases.interactor;

import entity.*;
import useCases.*;

public class PPTInteractor implements UseCaseInteractor {
    @Override
    public void interact(Block block, GameData data) {
        /**
         *  when player step on a property
         * @param  Block block
         * @param  GameData data
         */
        // player is owner
        if (OwnerIdentifier.isOwner(data.currentPlayer, (Property) block)) {
            OwnerPropertyUseCase.ownerUpgrade(data.currentPlayer, (Property) block);
        }
        // player is not owner
        else {
            PasserbyUseCase.passerbyArrival(data.currentPlayer, (Property) block,
                    ((Property) block).getOwner());
        }
    }
}
