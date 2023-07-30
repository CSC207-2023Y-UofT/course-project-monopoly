package entity;

import controller.ECInteractor;

/**
 * Represents the exam center block
 */
public class ExamCenter extends Block {
    public String getBlockName()
    {
        return "examcenter";
    }


    public ExamCenter(int ID) {
        super(ID);
    }

    @Override
    public void run(GameData data) {
        UseCaseInteractor interactor = new ECInteractor();
        interactor.interact(this, data);
    }
}
