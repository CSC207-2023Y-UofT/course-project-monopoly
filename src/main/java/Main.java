import controllers.GameController;
import controllers.InitController;
import entities.*;
import usecases.*;
import presenters.*;

/**
 * The Main class is the entry point for the game application. It initializes the game data, creates a game controller,
 * and runs the main game loop until the game is over.
 */
public class Main {
    public static GameController controller;
    public static GameData data;

    // File paths for game data initialization
    public static final String propertiesFile = "data/develop/revised_properties.csv";
    public static final String extraBlocksFile = "data/develop/extra_blocks_example.csv";
    public static final String[] destinyFiles = {"data/develop/destiny_card.csv", "data/develop/destiny_card.csv", "data/develop/destiny_card.csv"};

    /**
     * Uploads and initializes the game data using the data files specified.
     */
    public static void uploadData()
    {
        data = InitController.init(propertiesFile, extraBlocksFile, destinyFiles);

    }

    /**
     * The main method that runs the game loop and controls the flow of the game.
     * It starts by uploading data, creating a game controller, and then running the main game loop.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Upload and initialize the game data
        uploadData();

        // Create a game controller with the initialized game data
        controller = new GameController(data);
        // initialize the game board
        javax.swing.SwingUtilities.invokeLater(() -> {
            GameBoard frame = new GameBoard();
            frame.setVisible(true);

            // Run the main game loop until the game is over
            while(!controller.isGameOver())
            {
                OutputPresenter.notifyStartOfRound();
//                System.out.println("===================================\n\nCurrent Player is: Player"
//                        + data.currentPlayer.getUserId());
                Player currentplayer = data.currentPlayer;
                OutputPresenter.notifyTurn(currentplayer.getUserId());
                // Check if the current player is movable
                if(!controller.isCurrentMovable()) {
                    OutputPresenter.notifyRemainingStopRounds(currentplayer.getUserId(), StatusChecker.getRemainRounds(currentplayer));
//                    System.out.println("Player " + data.currentPlayerIndex + " cannot move.");
                    controller.settleOneRound();
                    frame.updateAll(data);
                    continue;
                }

                // Move the player and check if they pass the starting point
                // If the player passes the starting point, give them a bonus


                if(controller.playerRelativeWalk()) {

                    StartingPointUseCase.giveBonus(data.currentPlayer);
                    OutputPresenter.notifyPassingGO(data.currentPlayer.getUserId());
                }

                frame.playerMove(currentplayer.getUserId(), currentplayer.getPosition());
                PlayerInfoPanel.updatePanel(currentplayer.getUserId(), currentplayer.getMoney());


                // Get the current block and run its corresponding use case

                Block currentBlock = data.getBlockFromId(data.currentPlayer.getPosition()); // current BlockId

                currentBlock.run(data);
                frame.playerMove(currentplayer.getUserId(), currentplayer.getPosition());
                int level = ((Property) data.getBlockFromId(currentplayer.getPosition())).getLevel();
                frame.blockReplace(currentplayer.getPosition(), currentplayer.getUserId(), level);

                // Settle the round and move to the next player
                controller.settleOneRound();
                frame.updateAll(data);
            }

            // Finish the game and determine the winner
           Player winner = controller.finish();
            if(winner == null)
            {
                InputPresenter.notifyWinner();
            }
            else
                InputPresenter.notifyWinner(winner.getUserId());
        });

    }
}
