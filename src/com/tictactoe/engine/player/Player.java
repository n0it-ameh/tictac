package com.tictactoe.engine.player;

import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.play.Play;

public abstract class Player {
    private final PlayerType playerType;
    private final Alliance playerAlliance;
    private final Play playerPlay;
    private final boolean itsPlayerTurn;

    protected Player(final Play playerPlay) {
        this.playerPlay = playerPlay;
        this.playerAlliance = getPlayerAlliance();
        this.itsPlayerTurn = calculateTurn();
        playerType = getPlayerType();
    }

    public abstract boolean calculateTurn();
    public abstract Alliance getPlayerAlliance();
    public abstract PlayerType getPlayerType();
}
