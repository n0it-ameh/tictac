package com.tictactoe.engine.player;

import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.play.Play;

import java.util.Collection;

public class OPlayer extends Player {
    protected OPlayer(final Play playerPlay) {
        super(playerPlay);
    }

    @Override
    public Play getPlayerPlay() { return this.playerPlay; }

    public static OPlayer createOPlayer(final Play playerPlay){return new OPlayer(playerPlay);}

    @Override
    public boolean isHisTurn(Play playerPlay) {
        return playerPlay.getPlayAlliance() != this.playerAlliance;
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

    @Override
    public Collection<Play> getPlayerLegalMoves(final Play playerPlay) {
        return playerPlay.getOLegalPlays();
    }

    @Override
    public Collection<Play> getOpponentLegalMoves(Play playerPlay) {
        return playerPlay.getXLegalPlays();
    }

    @Override
    public Play executePlay(Play play, int destinationCoordX, int destinationCoordY) {
        return Play.playO(play, destinationCoordX, destinationCoordY);
    }


}
