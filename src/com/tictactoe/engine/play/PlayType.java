package com.tictactoe.engine.play;

import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.board.Line;
import com.tictactoe.engine.board.Tile;

import static com.tictactoe.engine.board.BoardUtils.playTank;

public enum PlayType {
    X_WINNING_PLAY,
    O_WINNING_PLAY,
    X_BLOCKING_PLAY,
    O_BLOCKING_PLAY,
    X_PREFERRED_PLAY,
    O_PREFERRED_PLAY,
    FORK_PLAY,
    FORK_BLOCK,
    PREFERRED_PLAY,
    NORMAL_PLAY;

    public static PlayType getPlayType(final Board board){
        final Board originalBoard = playTank.get(playTank.size() - 1);
        int countO = 0;
        int countX = 0;
        int countXBlock = 0;
        int countOBlock = 0;
        int countXOriginalBlock = 0;
        int countOOriginalBlock = 0;

        for(final Line line : originalBoard.getBoardLines()) {
            if(line.isOBlockingLine())
                countOOriginalBlock++;
            else if(line.isXBlockingLine())
                countXOriginalBlock++;
        }

        for(final Line line : board.getBoardLines()){
            if (line.isLineTrifectaO())
                countO++;
            else if (line.isLineTrifectaX())
                countX++;
            else if (line.isXBlockingLine())
                countXBlock++;
            else if (line.isOBlockingLine())
                countOBlock++;
        }

        if(countX > 0)
            return PlayType.X_WINNING_PLAY;
        else if(countO > 0)
            return PlayType.O_WINNING_PLAY;
        else if(countXBlock > countXOriginalBlock)
            return PlayType.X_BLOCKING_PLAY;
        else if(countOBlock > countOOriginalBlock)
            return PlayType.O_BLOCKING_PLAY;

        for(final Tile tile : board.getBoardTiles()){
            if(tile.getTileCoordX() == 1 &&
               tile.getTileCoordY() == 1 &&
               tile.isTileBiased()){
                if(tile.getAlliance() == Alliance.X)
                    return PlayType.X_PREFERRED_PLAY;
                else if(tile.getAlliance() == Alliance.O)
                    return PlayType.O_PREFERRED_PLAY;
            }
        }
        return PlayType.NORMAL_PLAY;
    }


}
