package com.tictactoe.tests;

import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.play.GameStatus;
import com.tictactoe.engine.play.PlayType;
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
import static com.tictactoe.engine.play.PlayType.*;

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

             if(alliance == 'x' && oPlayer.getPlayerType() == PlayerType.AI &&
                    playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING){
                xPlayer.executePlayerPlay(getX(coord), getY(coord));
                final Map<Board, Integer> evalO = new HashMap<>();
                for(final Board board22 : calculateOLegalPlays(playTank.get(playTank.size() - 1))){
                    evalO.put(board22, minimax(board22, 0, false));
                    if(getPlayType(board22) == PlayType.O_WINNING_PLAY)
                        evalO.put(board22, -1999999999);
                    else if(getPlayType(board22) == PlayType.O_BLOCKING_PLAY)
                        evalO.put(board22, -10000);

                }
                if(playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING) {
                    oPlayer.executePlayerLegalPlay(getBoardWithLowestEvaluation(evalO));
                }
            }
        }
        while (playTank.get(playTank.size() - 1).getGameStatus() == GameStatus.GAME_ON_GOING);
    }
}
