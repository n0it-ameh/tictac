package com.tictactoe.engine.board;

public class BoardEvaluation {
    private BoardEvaluation(){ throw new RuntimeException("not instantiable -> methodical class"); }
    /**
    * this board evaluation is relying on two player general score :
     * one player will be maximizing the score and the other shall
     * be the minimizer here maximizing player will be the X player
     * and the minimizer will be the O player .....................
     * generally this whole evaluation and decision making is based
     * on [minimax] and [zero-sum-game-theory algorithms]
     * you can read more details about these here in this wiki page :
     * https://en.wikipedia.org/wiki/Minimax
     * */

    public static int evaluate(final Board board){
        int count2X = 0, count2O = 0, count1X = 0, count1O = 0,
                count3X = 0, count3O = 0, countXB = 0, countOB = 0;

        for(final Line line : board.getBoardLines()){
            if(line.isLineTrifectaX())
                count3X++;
            else if(line.isLineTrifectaO())
                count3O++;
            if(line.hasDoubleX())
                count2X++;
            else if(line.hasDoubleO())
                count2O++;
            else if(line.hasSingleX())
                count1X++;
            else if(line.hasSingleO())
                count1O++;
            else if(line.isXBlockingLine())
                countXB++;
            else if(line.isOBlockingLine())
                countOB++;
        }
        return 10 * count3X + 6 * countXB + 3 * count2X + count1X -
                10 * count3O - 6 * countOB - 3 * count2O -count1O;
    }
}
