package com.tictactoe.engine.player;

import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.play.Play;

import java.util.Collection;

public class XPlayer extends Player {
    protected final Play playerPlay;
    protected final boolean itsPlayerTurn;
    protected final Collection<Play> playerLegalMoves;
    protected final Collection<Play> opponentLegalMoves;

    protected XPlayer(final Play playerPlay) {
        this.playerPlay = playerPlay;
        this.itsPlayerTurn = isHisTurn(playerPlay);
        this.playerLegalMoves = getPlayerLegalMoves(playerPlay);
        this.opponentLegalMoves = getOpponentLegalMoves(playerPlay);
    }

    @Override
    public Play getPlayerPlay() { return this.playerPlay; }
    public static XPlayer createXPlayer(){return new XPlayer(this.playerPlay);}
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

    @Override
    public Play executePlay(final Play play, final int destinationCoordX, final int destinationCoordY) {
        return Play.playX(play, destinationCoordX, destinationCoordY);
    }


}
