package com.tictactoe.engine;

public enum Alliance {
    X{
        @Override
        public Alliance nextPlay() {
            return O;
        }
    },
    O{
        @Override
        public Alliance nextPlay() {
            return X;
        }
    };

    public abstract Alliance nextPlay();
}
