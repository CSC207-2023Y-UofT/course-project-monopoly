package controllers;

import entities.*;
import presenters.InputPresenter;
import usecases.OwnerPropertyUseCase;
import usecases.generators.DestinyCardPoolGenerator;
import usecases.generators.PropertyGenerator;
import usecases.impactors.StatusImpactor;
import usecases.interactors.TTCInteractor;

import java.io.*;
import java.util.*;

/**
 * The InitController class is responsible for initializing the game data, including blocks, players, positions, and destiny cards.
 */
public class InitController {
    private static final String DELIMITER = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
    private static final int INIT_MONEY = 1500;
    private static final int PLAYER_NUM = 4;

    /**
     * Initializes the GameData based on the provided files and user input.
     *
     * @param propertiesFile  The file containing properties information for the game.
     * @param extraBlocksFile The file containing additional blocks information.
     * @param destinyFiles    An array of files containing destiny card information.
     * @return The initialized GameData instance.
     */
    public static GameData init(String propertiesFile, String extraBlocksFile, String[] destinyFiles) {
        // for GUI game, set the OwnerPropertyUseCase's presenter to GUI presenter
        InputPresenter presenter = new InputPresenter();
        OwnerPropertyUseCase.setInputPresenter(presenter);
        TTCInteractor.setInputPresenter(presenter);

        // blocks
        ArrayList<Block> blocks = new ArrayList<>(PropertyGenerator.generateProperties(propertiesFile));
        try(BufferedReader reader = new BufferedReader(new FileReader(extraBlocksFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                switch (values[1]) {
                    case "startingpoint":
                        blocks.add(new StartingPoint(Integer.parseInt(values[0]), Integer.parseInt(values[2])));
                        break;
                    case "ttcstation":
                        blocks.add(new TTCStation(Integer.parseInt(values[0])));
                        break;
                    case "examcenter":
                        blocks.add(new ExamCenter(Integer.parseInt(values[0])));
                        break;
                    case "destiny":
                        blocks.add(new Destiny(Integer.parseInt(values[0])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        blocks.sort(Comparator.comparing(Block::getId));

        // player
        Scanner scanner = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();
        int num = PLAYER_NUM;
        for (int i = 0; i < num; i++) {
            Player player = new Player(i, INIT_MONEY);
            StatusImpactor.initStatus(player);
            players.add(player);
        }
        System.out.println("Added players\n" + players);

        // position
        HashMap<Integer, ArrayList<Player>> position = new HashMap<>();
        for (int i = 0; i < blocks.size(); i++) {
            position.put(i, new ArrayList<>());
        }
        position.get(0).addAll(players);
        for (Player player: players) {
            player.setPosition(blocks.get(0).getId());
        }

        // destiny card
        int i = 0;
        for (Block block: blocks) {
            if (block instanceof Destiny) {
                DestinyCardPoolGenerator.generateDestinyCardPool(destinyFiles[i], (Destiny) block);
                i++;
            }
        }

        return new GameData(players.size(), blocks, players, position);
    }
}
