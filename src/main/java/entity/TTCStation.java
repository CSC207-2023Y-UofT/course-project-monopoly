package entity;

import useCases.interactor.TTCInteractor;

/**
 * Represents the TTC station block
 */
public class TTCStation extends Block {
    public TTCStation(int ID) {
        super(ID);
    }
    public String getBlockName()
    {
        return "ttcstation";
    }

    @Override
    public void run(GameData data) {
        UseCaseInteractor interactor = new TTCInteractor();
        interactor.interact(this, data);
    }
}
