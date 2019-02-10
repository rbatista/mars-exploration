package com.raphaelnegrisoli.elo7.marsexploration.model;

public class Probe {

    private int x;
    private int y;
    private CardinalDirection currentDirection;

    public Probe(final int x, final int y, final CardinalDirection initialDirection) {
        this.x = x;
        this.y = y;
        this.currentDirection = initialDirection;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CardinalDirection getCurrentDirection() {
        return currentDirection;
    }

    public void turnRight() {


    }
}
