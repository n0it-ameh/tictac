package com.tictactoe.engine.board;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.tictactoe.engine.Alliance;
import com.tictactoe.engine.play.Play;

import java.util.*;

import static com.tictactoe.engine.board.Board.newBoard;
import static com.tictactoe.engine.board.Tile.*;

public class BoardUtils {
    private BoardUtils() { throw new RuntimeException("non instantiable !!!"); }

    public static List<Board> playTank = new ArrayList<>();
    public static List<Alliance> allianceTank = new ArrayList<>();
    public static boolean isValid(final int coordX, final int coordY){
        return coordX >= 0 && coordX < 3 && coordY >= 0 && coordY < 3;
    }

    public static int getX(final int input){
        if(input == 1 || input == 2 || input == 3){
            return 2;
        }else if(input == 4 || input == 5 || input == 6){
            return 1;
        }else if(input == 7 || input == 8 || input == 9){
            return 0;
        }
            throw new RuntimeException("invalid X coordination !!");
    }

    public static int getY(final int input){
        if(input == 1 || input == 4 || input == 7){
            return 0;
        }else if(input == 2 || input == 5 || input == 8){
            return 1;
        }else if(input == 3 || input == 6 || input == 9){
            return 2;
        }
        throw new RuntimeException("invalid Y coordination !!");
    }

    public static int getTileNumericCoords(final Tile tile){
        final int CoordX = tile.getTileCoordX();
        final int CoordY = tile.getTileCoordY();
        if (CoordX == 0 && CoordY == 0)
            return 7;
        else if (CoordX == 0 && CoordY == 1)
            return 8;
        else if (CoordX == 0 && CoordY == 2)
            return 9;
        else if (CoordX == 1 && CoordY == 0)
            return 4;
        else if (CoordX == 1 && CoordY == 1)
            return 5;
        else if (CoordX == 1 && CoordY == 2)
            return 6;
        else if (CoordX == 2 && CoordY == 0)
            return 1;
        else if (CoordX == 2 && CoordY == 1)
            return 2;
        else if (CoordX == 2 && CoordY == 2)
            return 3;
        throw new RuntimeException("invalid Tile");
    }

    public static Board getBoardWithHighestEvaluation(final Map<Board, Integer> map){
        final Map<Board, Integer> map1 = map;
        int maxValueInMap=(Collections.max(map.values()));
        for (Map.Entry<Board, Integer> entry : map1.entrySet()) {
            if (entry.getValue()==maxValueInMap) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static Board getBoardWithLowestEvaluation(final Map<Board, Integer> map){
        final Map<Board, Integer> map2 = map;
        int minValueInMap = (Collections.min(map.values()));
        for(Map.Entry<Board, Integer> entry : map2.entrySet()) {
            if(entry.getValue()==minValueInMap) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static Board boardTestBuilder(final char i1, final char i2, final char i3,
                                         final char i4, final char i5, final char i6,
                                         final char i7, final char i8, final char i9) {
        final Table<Integer, Integer, Tile> boardC = HashBasedTable.create();
        final char[] alliances = {i1, i2, i3, i4, i5, i6, i7, i8, i9 };
        final int[] coords = {7, 8, 9, 4, 5, 6, 1, 2, 3};

        for(int i = 0; i < 9; i++){
            setTable(alliances[i], coords[i], boardC);
        }
        return newBoard(boardC);
    }

    public static void setTable(final char alliance, final int coord,
                                final Table<Integer, Integer, Tile> table){
        if(alliance == 'x'){
            table.put(getX(coord), getY(coord), X_BIASED_TILE_CACHE.get(getX(coord), getY(coord)));
        } else if(alliance == 'o'){
            table.put(getX(coord), getY(coord), O_BIASED_TILE_CACHE.get(getX(coord), getY(coord)));
        } else {
            table.put(getX(coord), getY(coord), EMPTY_TILE_CACHE.get(getX(coord), getY(coord)));
        }
    }

}
