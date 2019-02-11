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
    private Integer longitude;

    @NotNull
    @Min(0)
    private Integer latitude;

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

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(final Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(final Integer latitude) {
        this.latitude = latitude;
    }

    public String getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(final String currentDirection) {
        this.currentDirection = currentDirection;
    }

}
