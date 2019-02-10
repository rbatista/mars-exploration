package com.raphaelnegrisoli.elo7.marsexploration.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
public class Probe {

    private static final Logger LOGGER = LoggerFactory.getLogger(Probe.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer x;

    private Integer y;

    @Enumerated(EnumType.STRING)
    private CardinalDirection currentDirection;

    @ManyToOne
    private Plateau plateau;

    public Probe() {
    }

    public Probe(final Integer x, final Integer y, final CardinalDirection initialDirection,
                 final Plateau plateau) {
        this.x = x;
        this.y = y;
        this.currentDirection = initialDirection;
        this.plateau = plateau;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public CardinalDirection getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(CardinalDirection currentDirection) {
        this.currentDirection = currentDirection;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(final Plateau plateau) {
        this.plateau = plateau;
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
        if (!plateau.isValidCoordinate(x, y)) {
            final String message = String.format("Coordinate (%s, %s) is not valid. Aborting the command.", x, y);
            LOGGER.error("Move probe {} to invalid coordinate ({}, {})", this, x, y);
            throw new IllegalStateException(message);
        }

        if (!plateau.isCoordinateAvailable(x, y)) {
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Probe probe = (Probe) o;

        return new EqualsBuilder()
                .append(x, probe.x)
                .append(y, probe.y)
                .append(currentDirection, probe.currentDirection)
                .append(plateau, probe.plateau)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(x)
                .append(y)
                .append(currentDirection)
                .append(plateau)
                .toHashCode();
    }

}
