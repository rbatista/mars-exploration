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

    private Integer longitude;

    private Integer latitude;

    @Enumerated(EnumType.STRING)
    private CardinalDirection currentDirection;

    @ManyToOne
    private Plateau plateau;

    public Probe() {
    }

    public Probe(final Integer longitude, final Integer latitude, final CardinalDirection initialDirection,
                 final Plateau plateau) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.currentDirection = initialDirection;
        this.plateau = plateau;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
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
        validateCoordinate(longitude, latitude + 1);
        latitude += 1;
    }

    private void moveSouth() {
        validateCoordinate(longitude, latitude - 1);
        latitude -= 1;
    }

    private void moveEast() {
        validateCoordinate(longitude + 1, latitude);
        longitude += 1;
    }

    private void moveWest() {
        validateCoordinate(longitude - 1, latitude);
        longitude -= 1;
    }

    private void validateCoordinate(final Integer longitude, final Integer latitude) {
        if (!plateau.isValidCoordinate(longitude, latitude)) {
            final String message = String.format("Coordinate (%s, %s) is not valid. Aborting the command.", longitude, latitude);
            LOGGER.error("Move probe {} to invalid coordinate ({}, {})", this, longitude, latitude);
            throw new IllegalStateException(message);
        }

        if (!plateau.isCoordinateAvailable(longitude, latitude)) {
            final String message = String.format("Coordinate (%s, %s) already has another probe. " +
                    "Aborting the command.", longitude, latitude);
            LOGGER.error("Move probe {} to unavailable coordinate ({}, {})", this, longitude, latitude);
            throw new IllegalStateException(message);
        }
    }

    public boolean coordinateMatch(final Integer longitude, final Integer latitude) {
        return getLongitude().compareTo(longitude) == 0 && getLatitude().compareTo(latitude) == 0;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("longitude", longitude)
                .append("latitude", latitude)
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
                .append(longitude, probe.longitude)
                .append(latitude, probe.latitude)
                .append(currentDirection, probe.currentDirection)
                .append(plateau, probe.plateau)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(longitude)
                .append(latitude)
                .append(currentDirection)
                .append(plateau)
                .toHashCode();
    }

}
