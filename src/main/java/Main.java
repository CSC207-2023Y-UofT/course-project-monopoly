import controller.GameController;
import controller.InitController;
import entity.*;
import useCases.*;


public class Main {
    public static GameController controller;
    public static GameData data;


//    public static final String propertiesFile = "data/properties.csv";
    public static final String propertiesFile = "data/develop/properties_example.csv";

//    public static final String extraBlocksFile = "data/extra_blocks.csv";
    public static final String extraBlocksFile = "data/develop/extra_blocks_example.csv";

//    public static final String[] destinyFiles = {"data/test/destiny_card_test.csv", "data/test/destiny_card_test.csv", "data/test/destiny_card_test.csv"};
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

            switch (currentBlock.getBlockName())
            {
                case "property":
                {
                    controller.interactWithPPT((Property) currentBlock);
                    break;
                }
                case "ttcstation":
                {
                    assert currentBlock instanceof TTCStation;
                    controller.interactWithTTC((TTCStation) currentBlock );
                    break;

                }
                case "destiny":
                {
                    assert currentBlock instanceof Destiny;
                    controller.interactWithDestiny((Destiny) currentBlock);
                    break;
                }
                case "examcenter":
                {
                    controller.interactWithEC((ExamCenter) currentBlock);
                    break;
                }
            }

            controller.settleOneRound();
        }
        controller.finish();
    }
}
