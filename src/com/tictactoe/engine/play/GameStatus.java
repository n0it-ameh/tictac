package com.tictactoe.engine.play;

public enum GameStatus {

    GAME_ON_GOING{
        @Override
        public String toString(){
            return "-------------------";
        }
    },
    X_WON{
        @Override
        public String toString(){
            return "X Won !!!!!";
        }
    },
    O_WON{
        @Override
        public String toString(){
            return "O Won !!!!!";
        }
    },
    TIE{
        @Override
        public String toString(){
            return "BoardCompleted (TIE) ##";
        }
    },
    GAME_START{
        @Override
        public String toString() { return "Game Started"; }
    };
    @Override
    public abstract String toString();
}
