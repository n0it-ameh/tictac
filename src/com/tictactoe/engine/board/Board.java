package com.tictactoe.engine.board;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.tictactoe.engine.board.BoardUtils.isValid;
import static com.tictactoe.engine.board.Tile.EMPTY_TILE_CACHE;

public class Board {

    private final Table<Integer, Integer, Tile> board;
    private final Collection<Tile> emptyTiles;

    public static Board create(){
        return new Board(0, 0, null);
    }

    public Tile get(final int tileCoordX, final int tileCoordY){
        return this.board.get(tileCoordX, tileCoordY);
    }

    public static boolean hasEmptyTiles(Board board){
        return board.emptyTiles.size() > 0;
    }

    private Board(final int tileCoordX, final int tileCoordY, final Tile tile) {
        this.board = createBoard(tileCoordX, tileCoordY, tile);
        this.emptyTiles = calculateEmptyTiles();
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

    public static Table<Integer, Integer, Tile> createBoard(final int tileCoordX,
                                                             final int tileCoordY,
                                                             final Tile tile){
        final Table<Integer, Integer, Tile> board = HashBasedTable.create();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board.put(i, j, EMPTY_TILE_CACHE.get(i, j));
            }
        }
        if(isValid(tileCoordX, tileCoordY) && tile != null){
            board.put(tileCoordX, tileCoordY, tile);
        }
        return ImmutableTable.copyOf(board);
    }

    private Collection<Tile> calculateEmptyTiles(){
        final List<Tile> emptyTiles = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                final Tile tile;
                tile = board.get(i, j);
                if(tile == null){
                    emptyTiles.add(tile);
                }
            }
        }
        return ImmutableList.copyOf(emptyTiles);
    }

}
