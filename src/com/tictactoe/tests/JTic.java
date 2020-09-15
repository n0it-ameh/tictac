package com.tictactoe.tests;

import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.play.Play;

import java.util.Scanner;

import static com.tictactoe.engine.board.BoardUtils.*;
import static com.tictactoe.engine.play.GameStatus.GAME_ON_GOING;

public class JTic {
    public static void main(String[] args) {
        Board board1 = Board.create();
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
        while (playTank.get(playTank.size() - 1).checkStatus(playTank.get(playTank.size() - 1)) == GAME_ON_GOING);
    }
}
