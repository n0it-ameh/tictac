package com.tictactoe.engine;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

import java.util.*;

public abstract class Tile {

    protected final int tileCoordX;
    protected final int tileCoordY;
    public static final Table<Integer, Integer, Tile> EMPTY_TILE_CACHE = createAllPossibleEmptyTiles();
    public static final Table<Integer, Integer, Tile> X_BIASED_TILE_CACHE = createAllPossibleXTiles();
    public static final Table<Integer, Integer, Tile> O_BIASED_TILE_CACHE = createAllPossibleOTiles();

    protected Tile(final int tileCoordX, final int tileCoordY) {
        this.tileCoordX = tileCoordX;
        this.tileCoordY = tileCoordY;
    }

    public abstract boolean isTileBiased();
    public abstract Alliance getAlliance();

    public final int getTileCoordX(final Tile tile) {
        return tile.tileCoordX;
    }
    public final int getTileCoordY(final Tile tile) {
        return tile.tileCoordY;
    }


    private static Table<Integer, Integer, Tile> createAllPossibleXTiles(){
        final Table<Integer, Integer, Tile> xTileMap = HashBasedTable.create();
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                xTileMap.put(i, j, new BiasedTile(i, j, Alliance.X));
            }
        }
        return ImmutableTable.copyOf(xTileMap);
    }

    private static Table<Integer, Integer, Tile> createAllPossibleOTiles(){
        final Table<Integer, Integer, Tile> oTileMap = HashBasedTable.create();
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                oTileMap.put(i, j, new BiasedTile(i, j, Alliance.O));
            }
        }
        return ImmutableTable.copyOf(oTileMap);
    }

    private static Table<Integer, Integer, Tile> createAllPossibleEmptyTiles(){
        final Table<Integer, Integer, Tile> emptyTileMap = HashBasedTable.create();
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                emptyTileMap.put(i, j, new EmptyTile(i, j));
            }
        }
        return ImmutableTable.copyOf(emptyTileMap);
    }

    public static Tile createTile(final int tileCoordX, final int tileCoordY, final Alliance tileAlliance){
        if(tileAlliance != null){
            if(tileAlliance == Alliance.O){
                return O_BIASED_TILE_CACHE.get(tileCoordX, tileCoordY);
            }else{
                return X_BIASED_TILE_CACHE.get(tileCoordX, tileCoordY);
            }
        }else{
            return EMPTY_TILE_CACHE.get(tileCoordX, tileCoordY);
        }
    }

    public static class EmptyTile extends Tile {
        protected EmptyTile(final int tileCoordX, final int tileCoordY) { super(tileCoordX, tileCoordY); }
        @Override
        public boolean isTileBiased() { return false; }
        @Override
        public Alliance getAlliance() { return null; }
        @Override
        public String toString(){return "|   |";}
    }

    public static class BiasedTile extends Tile {
        private final Alliance tileAlliance;
        protected BiasedTile(final int tileCoordX, final int tileCoordY, final Alliance tileAlliance) {
            super(tileCoordX, tileCoordY);
            this.tileAlliance = tileAlliance;
        }
        @Override
        public boolean isTileBiased() { return true; }
        @Override
        public Alliance getAlliance() { return tileAlliance; }
        @Override
        public String toString(){ return tileAlliance == Alliance.X ? "| X |" : "| O |";}
    }
}
