package com.tictactoe.engine.board;

import com.google.common.base.Objects;
import com.google.common.collect.*;
import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.play.GameStatus;

import java.util.*;

import static com.tictactoe.engine.board.Line.*;
import static com.tictactoe.engine.board.Tile.*;

public class Board {

    private final Table<Integer, Integer, Tile> board;
    private final List<Tile> boardTiles;
    private final Collection<Tile> emptyTiles;
    private final Collection<Tile> xBiasedTiles;
    private final Collection<Tile> oBiasedTiles;
    private final List<Line> boardLines;
    private final GameStatus boardStatus;

    private Board(final Table<Integer, Integer, Tile> finalTable) {
        this.board = createBoard(finalTable);
        this.boardTiles = registerBoardTiles();
        this.emptyTiles = calculateEmptyTiles(board);
        this.xBiasedTiles = calculateXBiasedTiles(board);
        this.oBiasedTiles = calculateOBiasedTiles(board);
        this.boardLines = calculateBoardLines();
        this.boardStatus = calculateGameStatus();

    }

    private List<Tile> registerBoardTiles() {
        final List<Tile> listOfTiles = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                listOfTiles.add(board.get(i, j));
            }
        }
        return listOfTiles;
    }

    public List<Tile> getBoardTiles(){ return boardTiles; }

    private List<Line> calculateBoardLines() {
        Line[] arrayLine= new Line[8];
        final Tile tile7 = this.get(0, 0);
        final Tile tile8 = this.get(0, 1);
        final Tile tile9 = this.get(0, 2);
        final Tile tile4 = this.get(1, 0);
        final Tile tile5 = this.get(1, 1);
        final Tile tile6 = this.get(1, 2);
        final Tile tile1 = this.get(2, 0);
        final Tile tile2 = this.get(2, 1);
        final Tile tile3 = this.get(2, 2);

        arrayLine[0] = setLine(tile7, tile8, tile9);
        arrayLine[1] = setLine(tile4, tile5, tile6);
        arrayLine[2] = setLine(tile1, tile2, tile3);
        arrayLine[3] = setLine(tile7, tile4, tile1);
        arrayLine[4] = setLine(tile8, tile5, tile2);
        arrayLine[5] = setLine(tile9, tile6, tile3);
        arrayLine[6] = setLine(tile7, tile5, tile3);
        arrayLine[7] = setLine(tile9, tile5, tile1);

        return new ArrayList<>(Arrays.asList(arrayLine).subList(0, 8));
    }

    public List<Line> getBoardLines() { return this.boardLines; }

    public GameStatus getGameStatus(){ return this.boardStatus; }
    public Collection<Tile> getXBiasedTiles(){ return this.xBiasedTiles; }
    public Collection<Tile> getOBiasedTiles(){ return this.oBiasedTiles; }

    public static List<Board> calculateXLegalPlays(final Board board){
        final List<Board> xLegalPlays = new ArrayList<>();
        final Table<Integer, Integer, Tile> legal = HashBasedTable.create();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                legal.put(i, j, board.get(i, j));
            }
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                final Tile tile = board.get(i, j);
                if(!tile.isTileBiased()){
                    final Tile newTile = X_BIASED_TILE_CACHE.get(i, j);
                    legal.put(i, j, newTile);
                    final Board newBoard = newBoard(legal);
                    xLegalPlays.add(newBoard);
                    legal.put(i, j, board.get(i, j));
                }
            }
        }
        return ImmutableList.copyOf(xLegalPlays);
    }

    public static List<Board> calculateOLegalPlays(final Board board){
        final List<Board> oLegalPlays = new ArrayList<>();
        final Table<Integer, Integer, Tile> legal = HashBasedTable.create();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                legal.put(i, j, board.get(i, j));
            }
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                final Tile tile = board.get(i, j);
                if(!tile.isTileBiased()){
                    final Tile newTile = O_BIASED_TILE_CACHE.get(i, j);
                    legal.put(i, j, newTile);
                    final Board newBoard = newBoard(legal);
                    oLegalPlays.add(newBoard);
                    legal.put(i, j, board.get(i, j));
                }
            }
        }
        return ImmutableList.copyOf(oLegalPlays);
    }

    public static Table<Integer, Integer, Tile> convertToTable(final Board board){
        final Table<Integer, Integer, Tile> table = HashBasedTable.create();
        for(int i = 0 ; i < 3; i++){
            for(int j = 0; j < 3; j++){
                table.put(i, j, board.get(i, j));
            }
        }
        return ImmutableTable.copyOf(table);
    }


    private GameStatus calculateGameStatus() {
        final int emptyTilesNumber = this.emptyTiles.size();
        if (isXWinning())
            return GameStatus.X_WON;
        else if (isOWinning())
            return GameStatus.O_WON;
        else if (emptyTilesNumber == 0 && !isOWinning() && !isXWinning())
            return GameStatus.TIE;
        else if (emptyTilesNumber == 0 && isOWinning() && !isXWinning())
            return GameStatus.O_WON;
        else if (emptyTilesNumber == 0 && !isOWinning() && isXWinning())
            return GameStatus.X_WON;
        else if(emptyTilesNumber == 9 && !isOWinning() && !isXWinning())
            return GameStatus.GAME_START;
        return GameStatus.GAME_ON_GOING;
    }


    private boolean isXWinning() {
        int count = 0;
       for(final Line line : getBoardLines())
           if(line.isLineTrifectaX())
               count++;
          return count > 0;
    }

    public boolean isOWinning() {
        int count = 0;
       for(final Line line : getBoardLines())
           if(line.isLineTrifectaO())
               count++;
       return count > 0;
    }


    public static Board newBoard(final Table<Integer, Integer, Tile> board){
        return new Board(board);
    }

    public Tile get(final int tileCoordX, final int tileCoordY){ return this.board.get(tileCoordX, tileCoordY); }

    private static Table<Integer, Integer, Tile> createBoard(final Table<Integer, Integer, Tile> table){
        final Table<Integer, Integer, Tile> finalTable = HashBasedTable.create();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                finalTable.put(i, j, table.get(i, j));
            }
        }
        return ImmutableTable.copyOf(finalTable);
    }

    private Collection<Tile> calculateEmptyTiles(final Table<Integer, Integer, Tile> table){
        final List<Tile> emptyTiles = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final Tile tile = table.get(i, j);
                assert tile != null;
                if(!tile.isTileBiased()){
                    emptyTiles.add(tile);
                }
            }
        }
        return ImmutableList.copyOf(emptyTiles);
    }

    private Collection<Tile> calculateXBiasedTiles(final Table<Integer, Integer, Tile> table){
        final List<Tile> xTiles = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final Tile tile = table.get(i, j);
                assert tile != null;
                if(tile.isTileBiased()){
                    if(tile.getAlliance() == Alliance.X){
                        xTiles.add(tile);
                    }
                }
            }
        }
        return ImmutableList.copyOf(xTiles);
    }


    private Collection<Tile> calculateOBiasedTiles(final Table<Integer, Integer, Tile> table){
        final List<Tile> oTiles = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final Tile tile = table.get(i, j);
                assert tile != null;
                if(tile.isTileBiased()){
                    if(tile.getAlliance() == Alliance.O){
                        oTiles.add(tile);
                    }
                }
            }
        }
        return ImmutableList.copyOf(oTiles);
    }


    @Override
    public String toString(){
        final StringBuilder builder = new StringBuilder();
        for(int i=0; i < 3; i++){
            for(int j=0; j < 3; j++){
                final String tileText =
                        java.util.Objects.requireNonNull(this.board.get(i, j)).toString();
                builder.append(String.format("%3s", tileText));
                if((j + 1) % 3 == 0){
                    builder.append("\n");
                }
            }
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return Objects.equal(board, board1.board) &&
                Objects.equal(emptyTiles, board1.emptyTiles) &&
                Objects.equal(xBiasedTiles, board1.xBiasedTiles) &&
                Objects.equal(oBiasedTiles, board1.oBiasedTiles) &&
                boardStatus == board1.boardStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(board, emptyTiles, xBiasedTiles, oBiasedTiles, boardStatus);
    }
}
