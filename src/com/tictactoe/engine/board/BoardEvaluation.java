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
        int count2X = 0;
        int count2O = 0;
        int count1X = 0;
        int count1O = 0;

        for(final Line line : board.getBoardLines()){
            if(line.hasDoubleX())
                count2X++;
            else if(line.hasDoubleO())
                count2O++;
            else if(line.hasSingleX())
                count1X++;
            else if(line.hasSingleO())
                count1O++;
        }
        return 3 * count2X + count1X - 3 * count2O -count1O;
    }
}
