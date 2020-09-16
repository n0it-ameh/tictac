package com.tictactoe.tests;

import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.play.Play;
import com.tictactoe.engine.player.Player;
import com.tictactoe.engine.player.XPlayer;

import java.util.Scanner;

import static com.tictactoe.engine.board.BoardUtils.*;
import static com.tictactoe.engine.play.GameStatus.GAME_ON_GOING;
import static com.tictactoe.engine.player.OPlayer.createOPlayer;
import static com.tictactoe.engine.player.XPlayer.createXPlayer;

public class JTic {
    public static void main(String[] args) {
        Board board1 = Board.create();
        Play firstPlay = Play.createFirstPlay(board1);
        Player xPlayer = createXPlayer(firstPlay);
        //Player oPlayer = createOPlayer(firstPlay);
        Scanner sc = new Scanner(System.in);
        do {
            String input = sc.nextLine();
            int coord = Character.getNumericValue(input.charAt(1));
            char alliance = input.charAt(0);
            if (alliance == 'x') {
               // Play play = Play.playX(playTank.get(playTank.size() - 1), getX(coord), getY(coord));
                //Play play = xPlayer.executePlay(playTank.get(playTank.size() - 1), getX(coord), getY(coord));
            } else {
              //  Play play = Play.playO(playTank.get(playTank.size() - 1), getX(coord), getY(coord));
             //   Play play = oPlayer.executePlay(playTank.get(playTank.size() - 1), getX(coord), getY(coord));
            }
        }
        while (playTank.get(playTank.size() - 1).checkStatus(playTank.get(playTank.size() - 1)) == GAME_ON_GOING);
    }
}
