package com.tictactoe.tests;

import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.play.GameStatus;
import com.tictactoe.engine.player.OPlayer;
import com.tictactoe.engine.player.PlayerType;
import com.tictactoe.engine.player.XPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.tictactoe.engine.ai.Minimax.minimax;
import static com.tictactoe.engine.board.Board.calculateOLegalPlays;
import static com.tictactoe.engine.board.Board.calculateXLegalPlays;
import static com.tictactoe.engine.board.BoardUtils.*;
import static com.tictactoe.engine.board.BoardUtils.getY;
import static com.tictactoe.engine.board.Tile.*;
import static com.tictactoe.engine.play.Play.executePlay;

public class JHumanVsAi {
    public static void main(String[] args) {

        Board board = Board.newBoard(EMPTY_TILE_CACHE);
        playTank.add(board);
        allianceTank.add(null);
        final XPlayer xPlayer = XPlayer.getInstance();
        final OPlayer oPlayer = OPlayer.getInstance();



        Scanner sc = new Scanner(System.in);
        do {
            String input = sc.nextLine();
            int coord = Character.getNumericValue(input.charAt(1));
            char alliance = input.charAt(0);
            if (alliance == 'x' && oPlayer.getPlayerType() != PlayerType.AI) {
               xPlayer.executePlayerPlay(getX(coord), getY(coord));
            } else if (alliance == 'o' && xPlayer.getPlayerType() != PlayerType.AI){
                oPlayer.executePlayerPlay(getX(coord), getY(coord));
            }else if(alliance == 'x' && oPlayer.getPlayerType() == PlayerType.AI &&
                    playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING){
                xPlayer.executePlayerPlay(getX(coord), getY(coord));
                final Map<Board, Integer> evalO = new HashMap<>();
                final List<Board> oLegalPlays = calculateOLegalPlays(playTank.get(playTank.size() - 1));
                for(final Board board22 : oLegalPlays){
                    evalO.put(board22, minimax(board22, 0, false));
                }
                if(playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING) {
                    oPlayer.executePlayerLegalPlay(getBoardWithLowestEvaluation(evalO), xPlayer, oPlayer);
                }
            }else if(alliance == 'o' && xPlayer.getPlayerType() == PlayerType.AI &&
                    playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING){
                oPlayer.executePlayerPlay(getX(coord), getY(coord));
                final Map<Board, Integer> evalX = new HashMap<>();
                final List<Board> xLegalPlays = calculateXLegalPlays(playTank.get(playTank.size() - 1));
                for(final Board board22 : xLegalPlays){
                    evalX.put(board22, minimax(board22, 0, true));
                }
                if(playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING) {
                    xPlayer.executePlayerLegalPlay(getBoardWithHighestEvaluation(evalX), xPlayer, oPlayer);
                }
            }
        }
        while (playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING);
    }
}
