package com.tictactoe.tests;

import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.play.GameStatus;
import org.junit.jupiter.api.Test;

import static com.tictactoe.engine.board.BoardUtils.boardTestBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBoardWinning {
   @Test
   public void runTestWinningBoard() {

        Board winningBoard = boardTestBuilder('x', 'o', 'x',
                                              'x', 'x', 'o',
                                              'o', 'x', 'x');

        Board nonWinningBoard = boardTestBuilder('x', 'o', 'x',
                                                 'x', 'x', 'o',
                                                 'o', 'x', 'o');
        assertEquals(winningBoard.getGameStatus(), GameStatus.X_WON);
        assertEquals(nonWinningBoard.getGameStatus(), GameStatus.TIE);
    }

}