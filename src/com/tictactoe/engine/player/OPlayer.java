package com.tictactoe.engine.player;


import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.gui.LayOut;
import com.tictactoe.engine.gui.Settings;
import com.tictactoe.engine.play.PlayType;

import java.util.Collection;

import static com.tictactoe.engine.board.Board.calculateOLegalPlays;
import static com.tictactoe.engine.board.Board.calculateXLegalPlays;
import static com.tictactoe.engine.board.BoardUtils.playTank;
import static com.tictactoe.engine.board.Tile.EMPTY_TILE_CACHE;
import static com.tictactoe.engine.board.Tile.O_BIASED_TILE_CACHE;
import static com.tictactoe.engine.play.Play.executePlay;

public class OPlayer implements Player {
    private final PlayerType oPlayerType;
    private static final OPlayer HUMAN_INSTANCE = new OPlayer(PlayerType.HUMAN);
    private static final OPlayer AI_INSTANCE = new OPlayer(PlayerType.AI);

    private OPlayer(final PlayerType playerType){ oPlayerType = playerType; }
    public static OPlayer getHumanInstance() { return HUMAN_INSTANCE; }
    public static OPlayer getAiInstance() { return AI_INSTANCE; }
    @Override
    public Board getCurrentBoard(){ return playTank.get(playTank.size() - 1); }
    @Override
    public Board getPlayerBoard() { return getCurrentBoard(); }
    @Override
    public Collection<Board> getPlayerLegalPlays() { return calculateOLegalPlays(getPlayerBoard()); }
    @Override
    public Collection<Board> getOpponentLegalPlays() { return calculateXLegalPlays(getPlayerBoard()); }
    @Override
    public Board executePlayerPlay(final int tileCoordX, final int tileCoordY) {
        return executePlay(getCurrentBoard(), tileCoordX, tileCoordY,
                            O_BIASED_TILE_CACHE.get(tileCoordX, tileCoordY), Alliance.O);
    }
    @Override
    public Board executePlayerLegalPlay(final Board legalPlay) {
        return executePlay(legalPlay, 0,0,EMPTY_TILE_CACHE.get(0,0), Alliance.O);
    }
    @Override
    public PlayerType getPlayerType() { return oPlayerType; }
}
