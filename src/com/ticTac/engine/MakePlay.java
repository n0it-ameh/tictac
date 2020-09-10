package com.ticTac.engine;

public class MakePlay {
    private final int playCoordX;
    private final int playCoordY;
    private final Alliance playAlliance;
    private final Board currentBoard;

    public MakePlay(final Board currentBoard, final int playCoordX, final int playCoordY,
                    final Alliance playAlliance) {
        this.playCoordX = playCoordX;
        this.playCoordY = playCoordY;
        this.playAlliance = playAlliance;
        this.currentBoard = currentBoard;
    }




}
