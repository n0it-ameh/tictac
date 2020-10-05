package com.tictactoe.engine.player;

import com.tictactoe.engine.board.Board;

import java.util.Collection;

public interface Player {

      Board getPlayerBoard();
      Collection<Board> getPlayerLegalPlays();
      Collection<Board> getOpponentLegalPlays();
      Board executePlayerPlay(final int tileCoordX, final int tileCoordY);
      Board executePlayerLegalPlay(final Board legalPlay);
      Board getCurrentBoard();
      PlayerType getPlayerType();
}
