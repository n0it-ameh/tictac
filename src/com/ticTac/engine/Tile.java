package com.ticTac.engine;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

public abstract class Tile {
    protected final int tileCoordX;
    protected final int tileCoordY;
    public static final Table<Integer, Integer, Tile> EMPTY_TILE_CACHE = createEmptyTileEmpty();

    protected Tile(final int tileCoordX, final int tileCoordY) {
        this.tileCoordX = tileCoordX;
        this.tileCoordY = tileCoordY;
    }

    public static Tile createTile(final int tileCoordX, final int tileCoordY, final Alliance tileAlliance){
        return tileAlliance != null ? new OccupiedTile(tileCoordX, tileCoordY, tileAlliance) :
                EMPTY_TILE_CACHE.get(tileCoordX, tileCoordY);
    }

    private static Table<Integer, Integer, Tile> createEmptyTileEmpty(){
        final Table<Integer, Integer, Tile> emptyTileTable = HashBasedTable.create();
        for(int i=0; i < 3; i++){
            for(int j=0; j < 3; j++){
                emptyTileTable.put(i, j, new EmptyTile(i, j));
            }
        }
        return ImmutableTable.copyOf(emptyTileTable);
    }

    public abstract boolean isTileOccupied();
    public abstract Alliance getAlliance();

    public static final class OccupiedTile extends Tile{
        final Alliance tileAlliance;
        protected OccupiedTile(final int tileCoordX, final int tileCoordY,
                               final Alliance tileAlliance) {
            super(tileCoordX, tileCoordY);
            this.tileAlliance = tileAlliance;
        }
        @Override
        public boolean isTileOccupied() { return true; }
        @Override
        public Alliance getAlliance() { return tileAlliance; }
        @Override
        public String toString(){
            return getAlliance() == Alliance.X ? getAlliance().toString()
                    .replace(getAlliance().toString(), "X") :
                    getAlliance().toString().replace(getAlliance().toString(), "O");
        }
    }

    public static final class EmptyTile extends Tile {
        protected EmptyTile(final int tileCoordX, final int tileCoordY) {
            super(tileCoordX, tileCoordY);
        }
        @Override
        public boolean isTileOccupied() { return false; }
        @Override
        public Alliance getAlliance() { return null; }
        @Override
        public String toString(){ return "-"; }
    }
}
