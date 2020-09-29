package com.tictactoe.tests;


import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.play.GameStatus;

import java.util.Scanner;

import static com.tictactoe.engine.board.BoardUtils.*;
import static com.tictactoe.engine.board.Tile.*;
import static com.tictactoe.engine.play.Play.executePlay;


public class JTic {
    public static void main(String[] args) {
        Board board = Board.newBoard(EMPTY_TILE_CACHE);
        playTank.add(board);
        allianceTank.add(null);
        Scanner sc = new Scanner(System.in);
        do {
            String input = sc.nextLine();
            int coord = Character.getNumericValue(input.charAt(1));
            char alliance = input.charAt(0);
            if (alliance == 'x') {
                executePlay(playTank.get(playTank.size() - 1), getX(coord), getY(coord),
                        X_BIASED_TILE_CACHE.get(getX(coord), getY(coord)));
            } else {
                executePlay(playTank.get(playTank.size() - 1), getX(coord), getY(coord),
                        O_BIASED_TILE_CACHE.get(getX(coord), getY(coord)));
            }
        }
        while (playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING);
    }
}
