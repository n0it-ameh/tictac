package com.tictactoe.engine.ai;

import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.play.GameStatus;
import com.tictactoe.engine.play.PlayType;

import static com.tictactoe.engine.ai.Minimax.minimax;
import static com.tictactoe.engine.board.BoardEvaluation.evaluate;
import static com.tictactoe.engine.play.PlayType.*;
import static com.tictactoe.engine.play.PlayType.NORMAL_PLAY;

public class FiveHead {
    private FiveHead() { throw new RuntimeException("not instantiable !!"); }

    public static int fiveHead(final Board board, final boolean maximizing){
        int brain = minimax(board, 0, maximizing);
        if(getPlayType(board) == X_WINNING_PLAY && maximizing)
            return 1999999999;
        else if(getPlayType(board) == O_WINNING_PLAY && !maximizing)
            brain = -1999999999;
        else if(getPlayType(board) == X_BLOCKING_PLAY && maximizing)
            brain = 10000;
        else if(getPlayType(board) == O_BLOCKING_PLAY && !maximizing)
            brain = -10000;
        else if(getPlayType(board) == X_PREFERRED_PLAY && maximizing)
            brain = 8000;
        else if(getPlayType(board) == O_PREFERRED_PLAY && !maximizing)
            brain = -8000;

        return brain;
    }

}
