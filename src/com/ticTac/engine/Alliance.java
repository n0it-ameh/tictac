package com.ticTac.engine;

public enum Alliance {
    X{
        @Override
        public String toString() { return "X"; }
    },

    O{
        @Override
        public String toString() { return "O"; }
    },

    empty{
        @Override
        public String toString() { return "O"; }
    };

}
