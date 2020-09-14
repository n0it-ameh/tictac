package com.tictactoe.engine;

import com.google.common.collect.Table;

import java.util.Scanner;

import static com.tictactoe.engine.Alliance.*;
import static com.tictactoe.engine.Board.hasEmptyTiles;
import static com.tictactoe.engine.BoardUtils.getX;
import static com.tictactoe.engine.BoardUtils.getY;
import static com.tictactoe.engine.Tile.O_BIASED_TILE_CACHE;
import static com.tictactoe.engine.Tile.X_BIASED_TILE_CACHE;

public class JTic {
    public static void main(String[] args) {
        Board board1 = Board.create(0, 0, null);
        System.out.println(board1);
        Play play1 = Play.playX(board1, 2, 2);
        System.out.println(play1);
        Play play2 = Play.playO(play1, 0, 1);
        System.out.println(play2);
        Play play3 = Play.playX(play2, 1, 2);
        System.out.println(play3);
        Play play4 = Play.playO(board1, 2, 0);
        System.out.println(play4);
    }
}
