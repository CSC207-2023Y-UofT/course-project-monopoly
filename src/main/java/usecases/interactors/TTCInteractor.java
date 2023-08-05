package usecases.interactors;

import entities.*;
import usecases.impactors.PositionImpactor;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TTCInteractor implements UseCaseInteractor {
    @Override
    public void interact(Block block, GameData data) {
        /**
         *  when player step on TTC
         * @param  Block block
         * @param  GameData data
         */
        System.out.println("Enter a block id: ");
        Scanner scanner = new Scanner(System.in);
        int blockID;
        while (true) {
            try {
                blockID = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid id, try again: ");
                scanner.next();
                continue;
            }
            if (data.getBlockFromId(blockID).getBlockName().equals("ttcstation")) {
                System.out.println("Invalid move, try again: ");
                continue;
            }
            break;
        }

        PositionImpactor.absoluteMove(data,blockID);
        //UI: absoluteMove
        System.out.println("Player " + data.currentPlayer.getUserId() + " moved to " + data.currentPlayer.getPosition());
        Block newBlock = data.getBlockFromId(blockID);
        newBlock.run(data);
    }
}
