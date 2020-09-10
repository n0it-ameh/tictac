package com.ticTac;

import com.ticTac.engine.Board;

public class JTic {
    public static void main(String[] args) {
        Board board = Board.createStandardBoard();
        System.out.println(board);
    }
}
