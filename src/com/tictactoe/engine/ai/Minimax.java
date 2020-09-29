package com.tictactoe.engine.ai;

import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.play.GameStatus;
import com.tictactoe.engine.play.PlayType;

import static com.tictactoe.engine.board.Board.*;
import static com.tictactoe.engine.board.BoardEvaluation.evaluate;
import static com.tictactoe.engine.play.PlayType.getPlayType;

public class Minimax {
    private Minimax(){ throw new RuntimeException("not instantiable "); }
    /**
     * minimax function is typically a way to repeat some other evaluation
     * of game state in recursive approach to create a tree of game state
     * evaluation results for multiple states for either depth defined or
     * to the bottom leaf of the tree of possibilities. read more here :
     * https://en.wikipedia.org/wiki/Minimax .
     */

    public static int minimax(final Board board, int depth , final boolean maximizing){

        if(board.getGameStatus() != GameStatus.GAME_ON_GOING) {
            return maximizing ? evaluate(board) - depth * 1000 : evaluate(board) + depth * 1000;
        }
        else if(getPlayType(board) == PlayType.X_BLOCKING_PLAY) {
            return evaluate(board);
        }
        else if(getPlayType(board) == PlayType.O_BLOCKING_PLAY) {
            return evaluate(board);
        }

        else if(maximizing){
            int maxEval = Integer.MIN_VALUE;
            for(final Board child : calculateXLegalPlays(board)){
                int eval = minimax(child, depth++,false) - depth * 1000;
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for(final Board child : calculateOLegalPlays(board)){
                int eval = minimax(child, depth++,true) + depth * 1000;
                minEval = Math.min(minEval, eval);
            }
            return minEval;
        }
    }
}
