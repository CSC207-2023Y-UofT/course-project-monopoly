package Controller;

import entity.*;
import usecase.generator.DestinyCardPoolGenerator;
import usecase.generator.PropertyGenerator;
import usecase.impactor.StatusImpactor;

import java.io.*;
import java.util.*;

public class InitController {
    private static final String DELIMITER = ",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";
    private static final int INITMONEY = 1000;

    public static GameData init(String propertiesFile, String extraBlocksFile, String[] destinyFiles) {
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
        System.out.println("Enter the number of players: ");
        int num = scanner.nextInt();
        for (int i = 0; i < num; i++) {
            Player player = new Player(i, INITMONEY);
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
