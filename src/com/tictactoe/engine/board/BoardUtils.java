package com.tictactoe.engine.board;

import com.tictactoe.engine.play.Play;

import java.util.ArrayList;
import java.util.List;

public class BoardUtils {
    private BoardUtils() { throw new RuntimeException("non instantiable !!!"); }

    public static List<Play> playTank = new ArrayList<>();
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

}
