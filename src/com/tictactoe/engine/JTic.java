package com.tictactoe.engine;

import com.google.common.collect.Table;

import java.util.Scanner;

import static com.tictactoe.engine.Alliance.*;
import static com.tictactoe.engine.Board.hasEmptyTiles;
import static com.tictactoe.engine.BoardUtils.*;
import static com.tictactoe.engine.Tile.O_BIASED_TILE_CACHE;
import static com.tictactoe.engine.Tile.X_BIASED_TILE_CACHE;

public class JTic {
    public static void main(String[] args) {
        Board board1 = Board.create(0, 0, null);
        Play.createFirstPlay(board1);
        Scanner sc = new Scanner(System.in);
        do {
            String input = sc.nextLine();
            int coord = Character.getNumericValue(input.charAt(1));
            char type = input.charAt(0);
            if (type == 'x') {
                Play play = Play.playX(playTank.get(playTank.size() - 1), getX(coord), getY(coord));
            } else {
                Play play = Play.playO(playTank.get(playTank.size() - 1), getX(coord), getY(coord));
            }
        }
        while (!playTank.get(playTank.size() - 1).isBoardOver);
    }
}
