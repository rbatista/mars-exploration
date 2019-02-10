package com.raphaelnegrisoli.elo7.marsexploration.controller.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProbeDTO {

    private Integer id;

    @NotNull
    private Integer plateauId;

    @NotNull
    @Min(0)
    private Integer x;

    @NotNull
    @Min(0)
    private Integer y;

    @NotEmpty
    private String currentDirection;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getPlateauId() {
        return plateauId;
    }

    public void setPlateauId(final Integer plateauId) {
        this.plateauId = plateauId;
    }

    public Integer getX() {
        return x;
    }

    public void setX(final Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(final Integer y) {
        this.y = y;
    }

    public String getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(final String currentDirection) {
        this.currentDirection = currentDirection;
    }

}
