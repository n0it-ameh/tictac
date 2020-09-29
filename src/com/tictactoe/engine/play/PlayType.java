package com.tictactoe.engine.play;

import com.google.common.collect.ImmutableList;
import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.board.Line;
import com.tictactoe.engine.board.Tile;
import com.tictactoe.engine.player.PlayerType;

import java.util.ArrayList;
import java.util.List;

import static com.tictactoe.engine.board.BoardUtils.playTank;

public enum PlayType {
    WINNING_PLAY,
    X_BLOCKING_PLAY,
    O_BLOCKING_PLAY,
    FORK_PLAY,
    FORK_BLOCK,
    PREFERRED_PLAY,
    NORMAL_PLAY;

    public static PlayType getPlayType(final Board board){
        final Board originalBoard = playTank.get(playTank.size() - 1);
        int countOriginalBoardXBlock = 0;
        int countOriginalBoardOBlock = 0;

        for(final Line originalLine : originalBoard.getBoardLines() ) {
            if(originalLine.isOBlockingLine())
                countOriginalBoardOBlock++;
            else if(originalLine.isXBlockingLine())
                countOriginalBoardXBlock++;
        }

        for(final Line line : board.getBoardLines()){
            int countO = 0;
            int countX = 0;
            int countXBlock = 0;
            int countOBlock = 0;
            if (line.isLineTrifectaO())
                countO++;
            else if (line.isLineTrifectaX())
                countX++;
            else if (line.isXBlockingLine())
                countXBlock++;
            else if (line.isOBlockingLine())
                countOBlock++;

            if(countX == 3 || countO == 3)
                return PlayType.WINNING_PLAY;
            else if(countXBlock > countOriginalBoardXBlock)
                return PlayType.X_BLOCKING_PLAY;
            else if(countOBlock > countOriginalBoardOBlock)
                return PlayType.O_BLOCKING_PLAY;
        }
        return PlayType.NORMAL_PLAY;
    }



}
