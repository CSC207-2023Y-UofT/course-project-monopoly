import controllers.GameController;
import controllers.InitController;
import entities.*;
import usecases.*;


public class Main {
    public static GameController controller;
    public static GameData data;


    public static final String propertiesFile = "data/develop/revised_properties.csv";

    public static final String extraBlocksFile = "data/develop/extra_blocks_example.csv";

    public static final String[] destinyFiles = {"data/develop/destiny_card.csv", "data/develop/destiny_card.csv", "data/develop/destiny_card.csv"};
    public static void uploadData()
    {
        data = InitController.init(propertiesFile, extraBlocksFile, destinyFiles);

    }

    public static void main(String[] args) {
        uploadData();
        controller = new GameController(data);

        while(!controller.isGameOver())
        {
            System.out.println("===================================\n\nCurrent Player is: Player"
                    + data.currentPlayer.getUserId());

            if(!controller.isCurrentMovable()) {
                System.out.println("Player " + data.currentPlayerIndex + " cannot move.");
                controller.settleOneRound();
                // UI: update detain rounds + gameRounds + nextPlayer
                continue;
            }
            boolean isPassStartingPoint = controller.playerRelativeWalk();
            if(isPassStartingPoint)
            {
                StartingPointUseCase.giveBonus(data.currentPlayer);
            }
            // UI: call dice anime + update player position +

            Block currentBlock = data.getBlockFromId(data.currentPlayer.getPosition()); // current BlockId

            currentBlock.run(data);
            // use Strategy pattern to avoid using switch case


            controller.settleOneRound();
        }
        controller.finish();
    }
}
