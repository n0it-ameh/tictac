package com.tictactoe.engine.board;

import com.tictactoe.engine.Alliance;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private final Tile tile1;
    private final Tile tile2;
    private final Tile tile3;
    private final List<Tile> tileList;
    private final boolean isLineXBiased;
    private final boolean isLineOBiased;
    private final boolean isLineBlockedByX;
    private final boolean isLineBlockedByO;
    private final boolean isLineNeutral;
    private final boolean isLineSingleX;
    private final boolean isLineSingleO;
    private final boolean isLineDoubleX;
    private final boolean isLineDoubleO;

    private Line(final Tile tile1, final Tile tile2, final Tile tile3) {
        this.tile1 = tile1;
        this.tile2 = tile2;
        this.tile3 = tile3;
        this.tileList = registerTiles();
        this.isLineXBiased = isLineMagicX();
        this.isLineOBiased = isLineMagicO();
        this.isLineBlockedByX = isLineBlockedByX();
        this.isLineBlockedByO = isLineBlockedByO();
        this.isLineNeutral = isLineNeutral();
        this.isLineSingleX = isLineSingleX();
        this.isLineSingleO = isLineSingleO();
        this.isLineDoubleX = isLineDoubleX();
        this.isLineDoubleO = isLineDoubleO();
    }



    private List<Tile> registerTiles() {
        final List<Tile> tileList = new ArrayList<>();
        tileList.add(this.tile1);
        tileList.add(this.tile2);
        tileList.add(this.tile3);
        return tileList;
    }

    public List<Tile> getTileList() {
        return this.tileList;
    }


    public static Line setLine(final Tile tile1, final Tile tile2, final Tile tile3) {
        return tilesAreInTheSameLine(tile1, tile2, tile3) ? new Line(tile1, tile2, tile3) : null;
    }

    private static boolean tilesAreInTheSameLine(final Tile tile1, final Tile tile2, final Tile tile3) {
        final int[] tile1Coord = {tile1.getTileCoordX(), tile1.getTileCoordY()};
        final int[] tile2Coord = {tile2.getTileCoordX(), tile2.getTileCoordY()};
        final int[] tile3Coord = {tile3.getTileCoordX(), tile3.getTileCoordY()};

        if(tile1Coord[0] == tile2Coord[0] && tile2Coord[0] == tile3Coord[0])
            return true;
        else if(tile1Coord[1] == tile2Coord[1] && tile2Coord[1] == tile3Coord[1])
            return true;
        else if(tile1Coord[0] == tile1Coord[1] &&
                tile2Coord[0] == tile2Coord[1] && tile3Coord[0] == tile3Coord[1])
            return true;
        else return tile1Coord[0] + tile1Coord[1] == 2 &&
                    tile2Coord[0] + tile2Coord[1] == 2 &&
                    tile3Coord[0] + tile3Coord[1] == 2;
    }

    private boolean isLineMagicX(){
        return tile1.getAlliance() == Alliance.X &&
                tile2.getAlliance() == Alliance.X &&
                tile3.getAlliance() == Alliance.X;
    }

    private boolean isLineMagicO(){
        return tile1.getAlliance() == Alliance.O &&
                tile2.getAlliance() == Alliance.O &&
                tile3.getAlliance() == Alliance.O;
    }

    private boolean isLineBlockedByO() {
        int countX = 0;
        int countO = 0;
        for(final Tile tile : this.getTileList()) {
            if (tile.getAlliance() == Alliance.X)
                    countX++;
            else if (tile.getAlliance() == Alliance.O)
                countO++;
        }
        return countX == 2 && countO == 1;
    }

    private boolean isLineBlockedByX() {
        int countX = 0;
        int countO = 0;
        for(final Tile tile : this.getTileList()) {
            if (tile.getAlliance() == Alliance.X)
                countX++;
            else if (tile.getAlliance() == Alliance.O)
                countO++;
        }
        return countX == 1 && countO == 2;
    }

    private boolean isLineNeutral() {
        int countX = 0;
        int countO = 0;
        for(final Tile tile : this.getTileList()) {
            if (tile.getAlliance() == Alliance.X)
                countX++;
            else if (tile.getAlliance() == Alliance.O)
                countO++;
        }
        return countX == 1 && countO == 1;
    }

    private boolean isLineSingleX() {
        int countX = 0;
        int countO = 0;
        for(final Tile tile : this.getTileList()) {
            if (tile.getAlliance() == Alliance.X)
                countX++;
            else if (tile.getAlliance() == Alliance.O)
                countO++;
        }
        return countX == 1 && countO == 0;
    }

    private boolean isLineSingleO() {
        int countX = 0;
        int countO = 0;
        for(final Tile tile : this.getTileList()) {
            if (tile.getAlliance() == Alliance.X)
                countX++;
            else if (tile.getAlliance() == Alliance.O)
                countO++;
        }
        return countX == 0 && countO == 1;
    }

    private boolean isLineDoubleX() {
        int countX = 0;
        int countO = 0;
        for(final Tile tile : this.getTileList()) {
            if (tile.getAlliance() == Alliance.X)
                countX++;
            else if (tile.getAlliance() == Alliance.O)
                countO++;
        }
        return countX == 2 && countO == 1;
    }

    private boolean isLineDoubleO() {
        int countX = 0;
        int countO = 0;
        for(final Tile tile : this.getTileList()) {
            if (tile.getAlliance() == Alliance.X)
                countX++;
            else if (tile.getAlliance() == Alliance.O)
                countO++;
        }
        return countX == 0 && countO == 2;
    }


    public boolean isLineTrifectaX() { return isLineXBiased; }
    public boolean isLineTrifectaO() { return isLineOBiased; }
    public boolean isXBlockingLine() { return isLineBlockedByX; }
    public boolean isOBlockingLine() { return isLineBlockedByO; }
    public boolean isLineNeutralXO() { return isLineNeutral; }
    public boolean hasSingleX() { return isLineSingleX; }
    public boolean hasSingleO() { return isLineSingleO; }
    public boolean hasDoubleX() { return isLineDoubleX; }
    public boolean hasDoubleO() { return isLineDoubleO; }
}
