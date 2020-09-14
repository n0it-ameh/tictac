package com.tictactoe.engine;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.tictactoe.engine.BoardUtils.isValid;
import static com.tictactoe.engine.Tile.O_BIASED_TILE_CACHE;
import static com.tictactoe.engine.Tile.X_BIASED_TILE_CACHE;

public class Play {
    private final Table<Integer, Integer, Tile> board;
    protected final Tile tile;
    protected final int destinationCoordX;
    protected final int destinationCoordY;

    private Play(final Table<Integer, Integer, Tile> board, final Tile tile,
                 final int destinationCoordX, final int destinationCoordY) {
        this.board = board;
        this.tile = tile;
        this.destinationCoordX = destinationCoordX;
        this.destinationCoordY = destinationCoordY;
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

   public Tile get(final int tileCoordX, final int tileCoordY){
        return this.board.get(tileCoordX, tileCoordY);
   }

   public Collection<Play> getXLegalPlays(Board board){
        final List<Play> legalPlays = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(!this.board.get(i, j).isTileBiased()){
                    legalPlays.add(playX(board, i, j));
                }
            }
        }
        return ImmutableList.copyOf(legalPlays);
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
            if(isValid(destinationCoordX, destinationCoordY)){
                tileMap.put(destinationCoordX, destinationCoordY,
                        X_BIASED_TILE_CACHE.get(destinationCoordX,
                        destinationCoordY));
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
            if(isValid(destinationCoordX, destinationCoordY)){
                tileMap.put(destinationCoordX, destinationCoordY,
                        O_BIASED_TILE_CACHE.get(destinationCoordX,
                        destinationCoordY));
            }
        }
        return new Play(tileMap, O_BIASED_TILE_CACHE.get(destinationCoordX,
                destinationCoordY), destinationCoordX, destinationCoordY);
    }

    public static Play playX(final Board board,
                             final int destinationCoordX,
                             final int destinationCoordY){
        final Table<Integer, Integer, Tile> tileMap = HashBasedTable.create();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                final Tile tile = board.get(i, j);
                tileMap.put(i, j, tile);
            }
            if(isValid(destinationCoordX, destinationCoordY)){
                tileMap.put(destinationCoordX, destinationCoordY,
                        X_BIASED_TILE_CACHE.get(destinationCoordX,
                                destinationCoordY));
            }
        }
        return new Play(tileMap, X_BIASED_TILE_CACHE.get(destinationCoordX,
                destinationCoordY), destinationCoordX, destinationCoordY);
    }

    public static Play playO(final Board board,
                             final int destinationCoordX,
                             final int destinationCoordY){
        final Table<Integer, Integer, Tile> tileMap = HashBasedTable.create();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                final Tile tile = board.get(i, j);
                tileMap.put(i, j, tile);
            }
            if(isValid(destinationCoordX, destinationCoordY)){
                tileMap.put(destinationCoordX, destinationCoordY,
                        O_BIASED_TILE_CACHE.get(destinationCoordX,
                                destinationCoordY));
            }
        }
        return new Play(tileMap, O_BIASED_TILE_CACHE.get(destinationCoordX,
                destinationCoordY), destinationCoordX, destinationCoordY);
    }
}
