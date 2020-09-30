package com.tictactoe.tests;

import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.board.Line;
import com.tictactoe.engine.board.Tile;
import org.junit.jupiter.api.Test;

import static com.tictactoe.engine.board.Line.setLine;
import static com.tictactoe.engine.board.Tile.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLine {


    @Test
    public void runTestLine(){
        final Tile tile1 = createTile(2,0, Alliance.X);
        final Tile tile2 = createTile(1,1, Alliance.X);
        final Tile tile3 = createTile(0,2, Alliance.X);
        final Line line1 = setLine(tile1, tile2, tile3);

        final Tile tile4 = createTile(0,0, Alliance.O);
        final Tile tile5 = createTile(0,1, Alliance.O);
        final Tile tile6 = createTile(0,2, Alliance.O);
        final Line line2 = setLine(tile4, tile5, tile6);

        final Tile tile7 = createTile(2,0, Alliance.X);
        final Tile tile8 = createTile(1,1, Alliance.O);
        final Tile tile9 = createTile(0,2, Alliance.X);
        final Line line3 = setLine(tile7, tile8, tile9);

        final Tile tile10 = createTile(2,0, Alliance.X);
        final Tile tile11 = createTile(1,1, Alliance.O);
        final Tile tile12 = createTile(0,2, null);
        final Line line4 = setLine(tile10, tile11, tile12);

        assertTrue(line1.isLineTrifectaX());
        assertTrue(line2.isLineTrifectaO());
        assertFalse(line3.isLineTrifectaX());
        assertTrue(line3.isOBlockingLine());
        assertFalse(line4.isXBlockingLine());
        assertFalse(line4.isOBlockingLine());
        assertTrue(line4.isLineNeutralXO());

    }
}
