package com.tictactoe.engine;

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
        board1.setTile(0, 2, X_BIASED_TILE_CACHE.get(0, 2));
        System.out.println(board1);
    }
}
