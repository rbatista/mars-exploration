package com.raphaelnegrisoli.elo7.marsexploration.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Probe {

    private static final Logger LOGGER = LoggerFactory.getLogger(Probe.class);

    private int x;
    private int y;
    private CardinalDirection currentDirection;
    private final Plain plain;

    public Probe(final int x, final int y, final CardinalDirection initialDirection,
                 final Plain plain) {
        this.x = x;
        this.y = y;
        this.currentDirection = initialDirection;
        this.plain = plain;
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

    public void turnLeft() {
        this.currentDirection = currentDirection.getLeftDirection();
    }

    public void turnRight() {
        this.currentDirection = currentDirection.getRightDirection();
    }

    public void move() {
        switch (currentDirection) {
            case NORTH:
                moveNorth();
                break;
            case SOUTH:
                moveSouth();
                break;
            case EAST:
                moveEast();
                break;
            case WEST:
                moveWest();
                break;
        }
    }

    private void moveNorth() {
        validateCoordinate(x, y + 1);
        y += 1;
    }

    private void moveSouth() {
        validateCoordinate(x, y - 1);
        y -= 1;
    }

    private void moveEast() {
        validateCoordinate(x + 1, y);
        x += 1;
    }

    private void moveWest() {
        validateCoordinate(x - 1, y);
        x -= 1;
    }

    private void validateCoordinate(int x, int y) {
        if (!plain.isValidCoordinate(x, y)) {
            final String message = String.format("Coordinate (%s, %s) is not valid. Aborting the command.", x, y);
            LOGGER.error("Move probe {} to invalid coordinate ({}, {})", this, x, y);
            throw new IllegalStateException(message);
        }

        if (!plain.isAvailable(x, y)) {
            final String message = String.format("Coordinate (%s, %s) already has another probe. " +
                    "Aborting the command.", x, y);
            LOGGER.error("Move probe {} to unavailable coordinate ({}, {})", this, x, y);
            throw new IllegalStateException(message);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("x", x)
                .append("y", y)
                .append("currentDirection", currentDirection)
                .toString();
    }
}
