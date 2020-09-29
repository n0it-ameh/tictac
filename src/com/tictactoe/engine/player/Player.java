package com.tictactoe.engine.player;

import com.tictactoe.engine.board.Board;

import java.util.Collection;

public interface Player {

    public abstract Board getPlayerBoard();
    public abstract Collection<Board> getPlayerLegalPlays();
    public abstract Collection<Board> getOpponentLegalPlays();
    public abstract Board executePlayerPlay(final int tileCoordX, final int tileCoordY);
    public abstract Board executePlayerLegalPlay(final Board legalPlay, final XPlayer xPlayer, final OPlayer oPlayer);
    public abstract Board getCurrentBoard();
    public abstract PlayerType setPlayerType();
    public abstract PlayerType getPlayerType();
}
