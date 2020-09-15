package com.tictactoe.engine.player;

import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.play.Play;

public class OPlayer extends Player {
    protected OPlayer(Play playerPlay) {
        super(playerPlay);
    }

    @Override
    public boolean calculateTurn() {
        return false;
    }

    @Override
    public Alliance getPlayerAlliance() {
        return Alliance.O;
    }

    @Override
    public PlayerType getPlayerType() {
        //TODO to be assigned through gui radio button
        return PlayerType.HUMAN;
    }
}
