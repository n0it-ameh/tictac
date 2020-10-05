package com.tictactoe.engine.gui;

import com.google.common.util.concurrent.Uninterruptibles;
import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.board.Tile;
import com.tictactoe.engine.play.GameStatus;
import com.tictactoe.engine.player.PlayerType;

import javax.swing.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.tictactoe.engine.ai.FiveHead.fiveHead;
import static com.tictactoe.engine.board.Board.*;
import static com.tictactoe.engine.board.BoardUtils.*;
import static com.tictactoe.engine.board.Tile.EMPTY_TILE_CACHE;
import static com.tictactoe.engine.gui.LayOut.*;
import static com.tictactoe.engine.gui.Settings.*;
import static java.lang.Thread.sleep;

public class ExecutionGui {
    private ExecutionGui() {
        throw new RuntimeException("OMEGALUL !!");
    }
    private static int oWins = 0;
    private static int xWins = 0;
    private static int ties = 0;

    public static void link(final int input) {
        if (allianceTank.get(allianceTank.size() - 1) == Alliance.O && (gameAtStart() || isGuiBoardClean() ||
                playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING)) {
            // when clicking make x play and if o player is ai make o play after x
            if(oPlayer.getPlayerType() == PlayerType.HUMAN &&
                    (playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING ||
                            playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_START)) {
                xPlayer.executePlayerPlay(getX(input), getY(input));
            } else if(oPlayer.getPlayerType() == PlayerType.AI &&
                    (playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING ||
                            playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_START)){
                xPlayer.executePlayerPlay(getX(input), getY(input));
                final Map<Board, Integer> evalO = new HashMap<>();
                for(final Board board22 : calculateOLegalPlays(playTank.get(playTank.size() - 1))){
                    evalO.put(board22, fiveHead(board22, false));
                }
                if(playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING ||
                        playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_START) {
                    oPlayer.executePlayerLegalPlay(getBoardWithLowestEvaluation(evalO));
                }
            }
        } else {
            if(gameAtStart() || isGuiBoardClean() ||
                    playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING) {
                // when clicking make o play and if x player is ai make x play after o
                if(xPlayer.getPlayerType() == PlayerType.HUMAN &&
                        (playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING ||
                                playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_START)) {
                    oPlayer.executePlayerPlay(getX(input), getY(input));
                } else if(xPlayer.getPlayerType() == PlayerType.AI &&
                        (playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING ||
                                playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_START)) {
                    oPlayer.executePlayerPlay(getX(input), getY(input));
                    final Map<Board, Integer> evalX = new HashMap<>();
                    for (final Board board22 : calculateXLegalPlays(playTank.get(playTank.size() - 1))) {
                        evalX.put(board22, fiveHead(board22, true));
                    }
                    if (playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING ||
                            playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_START) {
                        xPlayer.executePlayerLegalPlay(getBoardWithHighestEvaluation(evalX));
                    }
                }
            }
        }
        updateGuiBoard();
        updateGameGui();
    }

    public static void updateGuiBoard() {
        final Board board = playTank.get(playTank.size() - 1);
        btn7.setText(board.get(0, 0).toString().charAt(2) + "");
        btn8.setText(board.get(0, 1).toString().charAt(2) + "");
        btn9.setText(board.get(0, 2).toString().charAt(2) + "");
        btn4.setText(board.get(1, 0).toString().charAt(2) + "");
        btn5.setText(board.get(1, 1).toString().charAt(2) + "");
        btn6.setText(board.get(1, 2).toString().charAt(2) + "");
        btn1.setText(board.get(2, 0).toString().charAt(2) + "");
        btn2.setText(board.get(2, 1).toString().charAt(2) + "");
        btn3.setText(board.get(2, 2).toString().charAt(2) + "");
    }

    public static void updateGameGui() {
        if(playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.X_WON ||
            playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.O_WON ||
            playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.TIE) {
            if (playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.X_WON) {
                xWins++;
                lblxscore.setText(xWins + "");
                lblGameScore.setText("X Won !!");
            } else if (playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.O_WON) {
                oWins++;
                lbloscore.setText(oWins + "");
                lblGameScore.setText("O Won !!");
            } else if (playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.TIE) {
                ties++;
                lblGameScore.setText("TIE #");
            }
            playTank.clear();
            playTank.add(newBoard(EMPTY_TILE_CACHE));
            allianceTank.clear();
            allianceTank.add(Alliance.O);
            updateGuiBoard();
            playXAI();
            updateGuiBoard();
        }
    }

    private static boolean isGuiBoardClean() {
        return !(btn1.getText().equals("X") || btn1.getText().equals("O")) &&
                !(btn2.getText().equals("X") || btn2.getText().equals("O")) &&
                !(btn3.getText().equals("X") || btn3.getText().equals("O")) &&
                !(btn4.getText().equals("X") || btn4.getText().equals("O")) &&
                !(btn5.getText().equals("X") || btn5.getText().equals("O")) &&
                !(btn6.getText().equals("X") || btn6.getText().equals("O")) &&
                !(btn7.getText().equals("X") || btn7.getText().equals("O")) &&
                !(btn8.getText().equals("X") || btn8.getText().equals("O")) &&
                !(btn9.getText().equals("X") || btn9.getText().equals("O"));
    }

    private static boolean gameAtStart() {
        return xWins == 0 && oWins == 0 && ties == 0;
    }
}