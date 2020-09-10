package com.ticTac.engine;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

import java.util.Collection;

import static com.ticTac.engine.Tile.EMPTY_TILE_CACHE;
import static com.ticTac.engine.Tile.createTile;


public class Board {
    private final Table<Integer, Integer, Tile> gameBoard;
    private final Collection<Alliance> xAlliance;
    private final Collection<Alliance> oAlliance;
    private final Player currentPlayer;

    private Board(final Builder builder) {
        this.gameBoard = createGameBoard(builder);
        this.xAlliance = null;
        this.oAlliance = null;
        this.currentPlayer = null;
    }

    @Override
    public String toString(){
        final StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                final String tileText = this.gameBoard.get(i, j).toString();
                builder.append(String.format("%3s", tileText));
                if((i+1) % 3 == 0){
                    builder.append("\n");
                }
            }
        }
        return builder.toString();
    }

    public Tile getTile(final int tileCoordX, final int tileCoordY){ return gameBoard.get(tileCoordX, tileCoordY);}

    private static Table<Integer, Integer, Tile> createGameBoard(final Builder builder){
        final Table<Integer, Integer, Tile> tiles = HashBasedTable.create();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                tiles.put(i, j, Tile.createTile(i, j, builder.boardConfig.get(i, j)));
            }
        }
        return ImmutableTable.copyOf(tiles);
    }

    public static Board createStandardBoard() {
        final Builder builder = new Builder();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                builder.setAlliance(i, j, createTile(i, j, Alliance.X));
            }
        }
        builder.setMoveMaker(Alliance.X);
        return builder.build();
    }

    public static class Builder {
        Table<Integer, Integer, Alliance> boardConfig;
        Alliance nextMoveMaker;
        public Builder() { this.boardConfig = HashBasedTable.create(); }
        public Builder setAlliance(final int alliancePosX, final int alliancePosY, final Tile tile) {
            this.boardConfig.put(alliancePosX, alliancePosY, tile.getAlliance());
            return this;
        }
        public Builder setMoveMaker(final Alliance nextMoveMaker) {
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }
        public Board build(){ return new Board(this); }
    }

}
