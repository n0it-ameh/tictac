package com.tictactoe.engine.play;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Table;
import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.tictactoe.engine.board.BoardUtils.isValid;
import static com.tictactoe.engine.board.BoardUtils.playTank;
import static com.tictactoe.engine.play.GameStatus.*;
import static com.tictactoe.engine.board.Tile.*;

public class Play {
    private final Table<Integer, Integer, Tile> board;
    protected final Tile tile;
    protected final int destinationCoordX;
    protected final int destinationCoordY;
    protected final Alliance playAlliance;
    protected final boolean isBoardOver;
    protected final GameStatus gameStatus;

    private Play(final Table<Integer, Integer, Tile> board, final Tile tile,
                 final int destinationCoordX, final int destinationCoordY, final Alliance playAlliance) {
        this.board = board;
        this.tile = tile;
        this.destinationCoordX = destinationCoordX;
        this.destinationCoordY = destinationCoordY;
        this.playAlliance = playAlliance;
        this.isBoardOver = isBoardComplete(this);
        System.out.println(this);
        playTank.add(this);
        this.gameStatus = checkStatus(this);
        printGameStatus(this);
    }

    public void printGameStatus(Play play){
        if(checkStatus(play) == X_WON){
            System.out.println(X_WON);
        } else if(checkStatus(play) == O_WON){
            System.out.println(O_WON);
        }else if(checkStatus(play) == TIE){
            System.out.println(TIE);
        }else{
            System.out.println(GAME_ON_GOING);
        }
    }

    public GameStatus checkStatus(Play play){
        if(isXWinning(play)){
            return X_WON;
        }else if(isOWinning(play)){
            return O_WON;
        }else if(isBoardComplete(play)){
            return TIE;
        }
        return GAME_ON_GOING;
    }

    public static boolean isBoardComplete(final Play play) {
        int biasedTiles = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(play.get(i, j).isTileBiased()){
                    biasedTiles++;
                }
            }
        }
        return biasedTiles == 9;
    }

    public boolean isXWinning(final Play play){
        final Alliance alliance = Alliance.X;
        int countDiag951 = 0;
        int countDiag753 = 0;
        for(int i = 0; i < 3; i++){
            if(play.get(i, 0).getAlliance() != null &&
               play.get(i, 1).getAlliance() != null &&
               play.get(i, 2).getAlliance() != null){
                if(play.get(i, 0).getAlliance() == alliance &&
                   play.get(i, 1).getAlliance() == alliance &&
                   play.get(i, 2).getAlliance() == alliance){
                    return true;
                }
            }else if(play.get(0, i).getAlliance() != null &&
               play.get(1, i).getAlliance() != null &&
               play.get(2, i).getAlliance() != null){
                if(play.get(0, i).getAlliance() == alliance &&
                play.get(1, i).getAlliance() == alliance &&
                play.get(2, i).getAlliance() == alliance){
                    return true;
                }
            }
        }
        for(int i = 0, j = 2; i < 3; i++,j--){
            if(play.get(i, j).getAlliance() != null){
                if(play.get(i, j).getAlliance() == alliance){
                    countDiag951++;
                }
            }
        }
        for(int i = 0, j = 0; i < 3; i++,j++){
            if(play.get(i, j).getAlliance() != null) {
                if (play.get(i, j).getAlliance() == alliance) {
                    countDiag753++;
                }
            }
        }
        return countDiag753 == 3 || countDiag951 == 3;
    }

    public boolean isOWinning(final Play play){
        final Alliance alliance = Alliance.O;
        int countDiag951 = 0;
        int countDiag753 = 0;
        for(int i = 0; i < 3; i++){
            if(play.get(i, 0).getAlliance() != null &&
                    play.get(i, 1).getAlliance() != null &&
                    play.get(i, 2).getAlliance() != null){
                if(play.get(i, 0).getAlliance() == alliance &&
                        play.get(i, 1).getAlliance() == alliance &&
                        play.get(i, 2).getAlliance() == alliance){
                    return true;
                }
            }else if(play.get(0, i).getAlliance() != null &&
                    play.get(1, i).getAlliance() != null &&
                    play.get(2, i).getAlliance() != null){
                if(play.get(0, i).getAlliance() == alliance &&
                        play.get(1, i).getAlliance() == alliance &&
                        play.get(2, i).getAlliance() == alliance){
                    return true;
                }
            }
        }
        for(int i = 0, j = 2; i < 3; i++,j--){
            if(play.get(i, j).getAlliance() != null){
                if(play.get(i, j).getAlliance() == alliance){
                    countDiag951++;
                }
            }
        }
        for(int i = 0, j = 0; i < 3; i++,j++){
            if(play.get(i, j).getAlliance() != null) {
                if (play.get(i, j).getAlliance() == alliance) {
                    countDiag753++;
                }
            }
        }
        return countDiag951 == 3 || countDiag753 == 3;
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

    public Collection<Play> getXLegalPlays() {
        final List<Play> legalPlays = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!this.get(i, j).isTileBiased()) {
                    legalPlays.add(playX(this, i, j));
                }
            }
        }
        return ImmutableList.copyOf(legalPlays);
    }

    public Collection<Play> getOLegalPlays() {
        final List<Play> legalPlays = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!this.get(i, j).isTileBiased()) {
                    legalPlays.add(playO(this, i, j));
                }
            }
        }
        return ImmutableList.copyOf(legalPlays);
    }

   public static Play createFirstPlay(final Board board){
        final Table<Integer, Integer, Tile> tileMap = HashBasedTable.create();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                final Tile tile = board.get(i, j);
                tileMap.put(i, j, tile);
            }
        }
        return new Play(tileMap, EMPTY_TILE_CACHE.get(0, 0),
                0, 0, null);
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
            if(isValid(destinationCoordX, destinationCoordY) && play.playAlliance != Alliance.X){
                tileMap.put(destinationCoordX, destinationCoordY,
                        X_BIASED_TILE_CACHE.get(destinationCoordX,
                        destinationCoordY));
            }
        }
        return new Play(tileMap, X_BIASED_TILE_CACHE.get(destinationCoordX, destinationCoordY),
                                             destinationCoordX, destinationCoordY, Alliance.X);

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
            if(isValid(destinationCoordX, destinationCoordY) && play.playAlliance != Alliance.O){
                tileMap.put(destinationCoordX, destinationCoordY,
                        O_BIASED_TILE_CACHE.get(destinationCoordX,
                        destinationCoordY));
            }
        }
        return new Play(tileMap, O_BIASED_TILE_CACHE.get(destinationCoordX, destinationCoordY),
                                             destinationCoordX, destinationCoordY, Alliance.O);
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
                    destinationCoordY), destinationCoordX, destinationCoordY, Alliance.X);
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
                    destinationCoordY), destinationCoordX, destinationCoordY, Alliance.O);
    }
}
