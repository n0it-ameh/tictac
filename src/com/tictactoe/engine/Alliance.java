package com.tictactoe.engine;

public enum Alliance {
    X{
        @Override
        public Alliance nextPlay() {
            return O;
        }

        @Override
        public String toString() {
            return "X";
        }
    },
    O{
        @Override
        public Alliance nextPlay() {
            return X;
        }

        @Override
        public String toString() {
            return "O";
        }
    };

    public abstract Alliance nextPlay();
    @Override
    public abstract String toString();
}
