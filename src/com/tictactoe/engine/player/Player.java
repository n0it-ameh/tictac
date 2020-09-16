package com.tictactoe.engine.player;

import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.play.Play;

import java.util.Collection;

public abstract class Player {
    protected final PlayerType playerType;
    protected final Alliance playerAlliance;
    protected final Play playerPlay;
    protected final boolean itsPlayerTurn;
    protected final Collection<Play> playerLegalMoves;
    protected final Collection<Play> opponentLegalMoves;

    protected Player(final Play playerPlay) {
        this.playerPlay = playerPlay;
        this.playerAlliance = getPlayerAlliance();
        this.itsPlayerTurn = isHisTurn(playerPlay);
        this.playerType = getPlayerType();
        this.playerLegalMoves = getPlayerLegalMoves(playerPlay);
        this.opponentLegalMoves = getOpponentLegalMoves(playerPlay);
    }

    public abstract boolean isHisTurn(final Play playerPlay);
    public abstract Alliance getPlayerAlliance();
    public abstract PlayerType getPlayerType();
    public abstract Collection<Play> getPlayerLegalMoves(final Play playerPlay);
    public abstract Collection<Play> getOpponentLegalMoves(final Play playerPlay);
    //TODO ####check in#####...........................
    public abstract Play executePlay(final Play play);
}
