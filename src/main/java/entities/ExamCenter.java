package entities;

import usecases.interactors.ECInteractor;

/**
 * Represents the Exam Center block on the game map.
 * This class extends the abstract Block class and provides specific behavior for the "Exam Center" block type.
 */
public class ExamCenter extends Block {

    /**
     * Constructs a new ExamCenter block with the specified ID.
     *
     * @param ID The unique identifier for the Exam Center block.
     */
    public ExamCenter(int ID) {
        super(ID);
    }

    /**
     * Retrieves the name of the block, which is "examcenter" for this type.
     *
     * @return The name of the block.
     */
    public String getBlockName()
    {
        return "examcenter";
    }

    /**
     * Executes the actions associated with the Exam Center block.
     * This method interacts with the `ECInteractor` to perform specific actions for the Exam Center block type.
     *
     * @param data The data structure representing the game state.
     */
    @Override
    public void run(GameData data) {
        UseCaseInteractor interactor = new ECInteractor();
        interactor.interact(this, data);
    }
}
