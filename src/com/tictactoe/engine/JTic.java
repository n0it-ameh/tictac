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
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
    }
}
