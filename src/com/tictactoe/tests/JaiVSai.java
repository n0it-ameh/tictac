package com.tictactoe.tests;

import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.play.GameStatus;
import com.tictactoe.engine.player.OPlayer;
import com.tictactoe.engine.player.PlayerType;
import com.tictactoe.engine.player.XPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tictactoe.engine.ai.Minimax.minimax;
import static com.tictactoe.engine.board.Board.calculateOLegalPlays;
import static com.tictactoe.engine.board.Board.calculateXLegalPlays;
import static com.tictactoe.engine.board.BoardUtils.*;
import static com.tictactoe.engine.board.Tile.EMPTY_TILE_CACHE;

public class JaiVSai {
    public static void main(String[] args) {
        Board board = Board.newBoard(EMPTY_TILE_CACHE);
        playTank.add(board);
        allianceTank.add(null);
        final XPlayer xPlayer = XPlayer.getInstance();
        final OPlayer oPlayer = OPlayer.getInstance();

        if(xPlayer.getPlayerType() == PlayerType.AI && oPlayer.getPlayerType() == PlayerType.AI) {
            while (playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING) {
                final Map<Board, Integer> evalO = new HashMap<>();
                final Map<Board, Integer> evalX = new HashMap<>();
                final List<Board> oLegalPlays = calculateOLegalPlays(playTank.get(playTank.size() - 1));
                for(final Board board22 : oLegalPlays){
                    evalO.put(board22, minimax(board22, 0, false));
                }
                if(playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING) {
                    oPlayer.executePlayerLegalPlay(getBoardWithLowestEvaluation(evalO), xPlayer, oPlayer);
                }

                /*changing turn**/
                final List<Board> xLegalPlays = calculateXLegalPlays(playTank.get(playTank.size() - 1));
                for(final Board board22 : xLegalPlays){
                    evalX.put(board22, minimax(board22, 0, true));
                }
                if(playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING) {
                    xPlayer.executePlayerLegalPlay(getBoardWithHighestEvaluation(evalX), xPlayer, oPlayer);
                }
            }
        }
    }
}
