package UI;
public class GameMain {
    private GameBoard gameBoard;

    public GameMain() {
        // Create instances of game elements
        gameBoard = new GameBoard();

        // Initialize the game elements and set up the game board

        // Start the game loop
        gameLoop();
    }

    private void gameLoop() {
        while (true) {



            }
        }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new GameMain();
        });
    }
}
