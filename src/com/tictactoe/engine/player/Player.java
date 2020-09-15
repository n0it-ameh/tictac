package com.tictactoe.engine.player;

import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.play.Play;

public abstract class Player {
    private final Alliance playerAlliance;
    private final Play playerPlay;
    private final boolean itsPlayerTurn;

    protected Player(final Play playerPlay) {
        this.playerPlay = playerPlay;
        this.playerAlliance = getPlayerAlliance();
        this.itsPlayerTurn = calculateTurn();
    }

    public abstract boolean calculateTurn();
    public abstract Alliance getPlayerAlliance();
}
