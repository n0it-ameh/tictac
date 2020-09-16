package com.tictactoe.engine.player;

import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.play.Play;

import java.util.Collection;

public abstract class Player {
    protected final PlayerType playerType;
    protected final Alliance playerAlliance;


    protected Player() {
        this.playerAlliance = getPlayerAlliance();
        this.playerType = getPlayerType();

    }

    public abstract Play getPlayerPlay();

    public abstract boolean isHisTurn(final Play playerPlay);
    public abstract Alliance getPlayerAlliance();
    public abstract PlayerType getPlayerType();
    public abstract Collection<Play> getPlayerLegalMoves(final Play playerPlay);
    public abstract Collection<Play> getOpponentLegalMoves(final Play playerPlay);
    //TODO ####check in#####...........................
    public abstract Play executePlay(final Play play, final int destinationCoordX, final int destinationCoordY);
}
