package com.tictactoe.engine.gui;

import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.play.GameStatus;
import com.tictactoe.engine.player.PlayerType;

import javax.swing.*;

import java.util.HashMap;
import java.util.Map;

import static com.tictactoe.engine.ai.FiveHead.fiveHead;
import static com.tictactoe.engine.board.Board.calculateOLegalPlays;
import static com.tictactoe.engine.board.Board.calculateXLegalPlays;
import static com.tictactoe.engine.board.BoardUtils.*;
import static com.tictactoe.engine.gui.LayOut.oPlayer;
import static com.tictactoe.engine.gui.LayOut.xPlayer;

public class ExecutionGui {
    private ExecutionGui() {
        throw new RuntimeException("OMEGALUL !!");
    }

    public static void link(final int input) {
        System.out.println(oPlayer.getPlayerType());
        System.out.println(xPlayer.getPlayerType());
        if (allianceTank.get(allianceTank.size() - 1) == Alliance.O) {
            if ((playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING ||
                    playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_START) &&
                oPlayer.getPlayerType() == PlayerType.HUMAN) {
                xPlayer.executePlayerPlay(getX(input), getY(input));
                updateGuiBoard();

            } else if((playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING ||
                    playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_START) &&
                    oPlayer.getPlayerType() == PlayerType.AI) {
                xPlayer.executePlayerPlay(getX(input), getY(input));
                updateGuiBoard();
                final Map<Board, Integer> evalO = new HashMap<>();
                for(final Board board22 : calculateOLegalPlays(playTank.get(playTank.size() - 1))){
                    evalO.put(board22, fiveHead(board22, false));
                }if(playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING ||
                        playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_START) {
                    oPlayer.executePlayerLegalPlay(getBoardWithLowestEvaluation(evalO));
                }
                updateGuiBoard();
            }
        } else {
            if ((playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING ||
                    playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_START) &&
                    xPlayer.getPlayerType() == PlayerType.HUMAN) {
                oPlayer.executePlayerPlay(getX(input), getY(input));
                updateGuiBoard();
            } else if ((playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING ||
                    playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_START) &&
                    oPlayer.getPlayerType() == PlayerType.AI) {
                oPlayer.executePlayerPlay(getX(input), getY(input));
                updateGuiBoard();
                final Map<Board, Integer> evalX = new HashMap<>();
                for(final Board board22 : calculateXLegalPlays(playTank.get(playTank.size() - 1))){
                    evalX.put(board22, fiveHead(board22, true));
                }if(playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING ||
                        playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_START) {
                    xPlayer.executePlayerLegalPlay(getBoardWithLowestEvaluation(evalX));
                }
                updateGuiBoard();
            }
        }
    }

    private static void updateGuiBoard() {
        final Board board = playTank.get(playTank.size() - 1);
        LayOut.btn7.setText(board.get(0, 0).toString().charAt(2) + "");
        LayOut.btn8.setText(board.get(0, 1).toString().charAt(2) + "");
        LayOut.btn9.setText(board.get(0, 2).toString().charAt(2) + "");
        LayOut.btn4.setText(board.get(1, 0).toString().charAt(2) + "");
        LayOut.btn5.setText(board.get(1, 1).toString().charAt(2) + "");
        LayOut.btn6.setText(board.get(1, 2).toString().charAt(2) + "");
        LayOut.btn1.setText(board.get(2, 0).toString().charAt(2) + "");
        LayOut.btn2.setText(board.get(2, 1).toString().charAt(2) + "");
        LayOut.btn3.setText(board.get(2, 2).toString().charAt(2) + "");
    }
}
