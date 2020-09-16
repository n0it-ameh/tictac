package com.tictactoe.engine.player;

import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.play.Play;

import java.util.Collection;

public class XPlayer extends Player {
    protected XPlayer(final Play playerPlay) { super(playerPlay); }

    @Override
    public boolean isHisTurn(Play playerPlay) {
        return playerPlay.getPlayAlliance() != this.playerAlliance;
    }

    @Override
    public Alliance getPlayerAlliance() {
        return Alliance.X;
    }

    @Override
    public PlayerType getPlayerType() {
        //TODO to be assigned through gui radio button
        return PlayerType.HUMAN;
    }

    @Override
    public Collection<Play> getPlayerLegalMoves(final Play playerPlay) {
        return playerPlay.getXLegalPlays();
    }

    @Override
    public Collection<Play> getOpponentLegalMoves(Play playerPlay) {
        return playerPlay.getOLegalPlays();
    }


}
