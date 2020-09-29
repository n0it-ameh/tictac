package com.tictactoe.engine.play;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.board.Board;
import com.tictactoe.engine.board.Tile;

import static com.tictactoe.engine.board.Board.newBoard;
import static com.tictactoe.engine.board.BoardUtils.*;

public class Play {
    private final Board playedBoard;
    private final Tile playTile;
    private final Alliance playAlliance;
    private final Board currentBoard;
    private final int destinationTileCoordX;
    private final int destinationTileCoordY;

    private Play(final Board currentBoard, final int tileCoordX, final int tileCoordY, final Tile playTile) {
        this.playedBoard = generatePlayedBoard(currentBoard, tileCoordX, tileCoordY, playTile);
        this.playTile = playTile;
        this.playAlliance = playTile.getAlliance();
        this.currentBoard = currentBoard;
        this.destinationTileCoordX = tileCoordX;
        this.destinationTileCoordY = tileCoordY;
        System.out.println("new play instance");
    }

    public static Board executePlay(final Board currentBoard, final int tileCoordX,
                                    final int tileCoordY, final Tile playTile){
        final Play play = new Play(currentBoard, tileCoordX, tileCoordY, playTile);
        playTank.add(play.getPlayedBoard());
        allianceTank.add(play.playAlliance);
        System.out.println(play.getPlayedBoard());
        System.out.println(play.getPlayedBoard().getGameStatus());
        return play.getPlayedBoard();
    }

    public static Board executePlay(final Play play){
        return executePlay(play.currentBoard, play.destinationTileCoordX,
                                play.destinationTileCoordY, play.playTile);
    }



    public Board getPlayedBoard(){ return this.playedBoard; }

    private Tile getPlayTile(){return this.playTile;}

    private Board generatePlayedBoard(final Board currentBoard, final int tileCoordX,
                                      final int tileCoordY, final Tile tile){
        final Table<Integer, Integer, Tile> customPlay = HashBasedTable.create();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                customPlay.put(i, j, currentBoard.get(i, j));
            }
        }
        if(isValid(tileCoordX, tileCoordY) && !customPlay.get(tileCoordX, tileCoordY).isTileBiased() &&
            tile.getAlliance() != allianceTank.get(allianceTank.size() - 1)){
            customPlay.put(tileCoordX, tileCoordY, tile);
        }
        return newBoard(customPlay);
    }

}
