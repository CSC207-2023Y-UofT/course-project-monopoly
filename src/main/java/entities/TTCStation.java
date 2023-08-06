package entities;

import usecases.interactors.TTCInteractor;

/**
 * Represents the TTC station block
 */
public class TTCStation extends Block {

    /**
     * Class constructor to create a new TTCStation block with the specified ID.
     *
     * @param ID The unique identifier for the TTCStation block.
     */
    public TTCStation(int ID) {
        super(ID);
    }

    /**
     * Gets the name of the block, which is "ttcstation" for this type.
     *
     * @return The name of the block.
     */
    public String getBlockName()
    {
        return "ttcstation";
    }

    /**
     * Executes the actions associated with the TTCStation block.
     * This method interacts with the `TTCInteractor` to perform specific actions for the TTCStation block type.
     *
     * @param data The data structure representing the game state.
     */
    @Override
    public void run(GameData data) {
        UseCaseInteractor interactor = new TTCInteractor();
        interactor.interact(this, data);
    }

}
