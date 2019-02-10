package com.raphaelnegrisoli.elo7.marsexploration.controller.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PlateauDTO {

    private Integer id;

    @NotNull
    @Min(0)
    private int width;

    @NotNull
    @Min(0)
    private int height;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("width", width)
                .append("height", height)
                .toString();
    }
}
