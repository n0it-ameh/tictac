package com.tictactoe.engine.player;


import com.tictactoe.engine.board.Board;

import java.util.Collection;

import static com.tictactoe.engine.board.Board.calculateOLegalPlays;
import static com.tictactoe.engine.board.Board.calculateXLegalPlays;
import static com.tictactoe.engine.board.BoardUtils.playTank;
import static com.tictactoe.engine.board.Tile.EMPTY_TILE_CACHE;
import static com.tictactoe.engine.board.Tile.X_BIASED_TILE_CACHE;
import static com.tictactoe.engine.play.Play.executePlay;

public class XPlayer implements Player {
    private final PlayerType xPlayerType;
    private static final XPlayer INSTANCE = new XPlayer();

    private XPlayer(){
        this.xPlayerType = setPlayerType();
    }
    public static XPlayer getInstance() { return INSTANCE; }
    @Override
    public Board getCurrentBoard(){ return playTank.get(playTank.size() - 1); }
    @Override
    public Board getPlayerBoard() { return getCurrentBoard(); }
    @Override
    public Collection<Board> getPlayerLegalPlays() { return calculateXLegalPlays(getPlayerBoard()); }
    @Override
    public Collection<Board> getOpponentLegalPlays() { return calculateOLegalPlays(getPlayerBoard()); }
    @Override
    public Board executePlayerPlay(final int tileCoordX, final int tileCoordY) {
        return executePlay(getCurrentBoard(), tileCoordX, tileCoordY,
                            X_BIASED_TILE_CACHE.get(tileCoordX, tileCoordY));
    }
    @Override
    public Board executePlayerLegalPlay(final Board legalPlay, final XPlayer xPlayer, final OPlayer oPlayer) {
        return executePlay(legalPlay, 0,0,EMPTY_TILE_CACHE.get(0,0));
    }
    @Override
    public  PlayerType setPlayerType() {
        //TODO to be assigned through gui radio button
        return PlayerType.AI;
    }
    @Override
    public PlayerType getPlayerType() { return this.xPlayerType; }
}
