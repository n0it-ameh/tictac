package com.tictactoe.engine;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

import static com.tictactoe.engine.Tile.O_BIASED_TILE_CACHE;
import static com.tictactoe.engine.Tile.X_BIASED_TILE_CACHE;

public class Play {
    private final Table<Integer, Integer, Tile> board;
    private final Tile tile;
    private final int destinationCoordX;
    private final int destinationCoordY;

    private Play(final Table<Integer, Integer, Tile> board, final Tile tile,
                 final int destinationCoordX, final int destinationCoordY) {
        this.board = board;
        this.tile = tile;
        this.destinationCoordX = destinationCoordX;
        this.destinationCoordY = destinationCoordY;
    }

    public static Play create(final Board board, final Tile tile,
                              final int destinationCoordX,
                              final int destinationCoordY){
        final Table<Integer, Integer, Tile> table = HashBasedTable.create();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                table.put(i, j, board.get(i, j));
            }
        }
        return new Play(table, tile, destinationCoordX, destinationCoordY);
    }

    @Override
    public String toString(){
        final StringBuilder builder = new StringBuilder();
        for(int i=0; i < 3; i++){
            for(int j=0; j < 3; j++){
                final String tileText = this.board.get(i, j).toString();
                builder.append(String.format("%3s", tileText));
                if((j + 1) % 3 == 0){
                    builder.append("\n");
                }
            }
        }
        return builder.toString();
    }


    public Table<Integer, Integer, Tile> makePlay(){
        final Table<Integer, Integer, Tile> playBoard = HashBasedTable.create();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                playBoard.put(i, j, board.get(i, j));
            }
        }
        return ImmutableTable.copyOf(playBoard);
    }

   public Tile get(final int tileCoordX, final int tileCoordY){
        return this.board.get(tileCoordX, tileCoordY);
   }

    public static Play playX(final Play play,
                             final int destinationCoordX,
                             final int destinationCoordY){
        final Table<Integer, Integer, Tile> tileMap = HashBasedTable.create();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                final Tile tile = play.get(i, j);
                tileMap.put(i, j, tile);
            }
        }
        return new Play(tileMap, X_BIASED_TILE_CACHE.get(destinationCoordX,
                destinationCoordY), destinationCoordX, destinationCoordY);
    }

    public static Play playO(final Play play,
                             final int destinationCoordX,
                             final int destinationCoordY){
        final Table<Integer, Integer, Tile> tileMap = HashBasedTable.create();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                final Tile tile = play.get(i, j);
                tileMap.put(i, j, tile);
            }
        }
        return new Play(tileMap, O_BIASED_TILE_CACHE.get(destinationCoordX,
                destinationCoordY), destinationCoordX, destinationCoordY);
    }
}
