package com.raphaelnegrisoli.elo7.marsexploration.model;

import java.util.ArrayList;
import java.util.List;

public class Plain {

    private final int width;
    private final int height;
    private final List<Probe> probes;

    public Plain(int width, int height) {
        this.width = width;
        this.height = height;
        this.probes = new ArrayList<>();
    }

    public int getWidth() {

        return width;
    }

    public int getHeight() {

        return height;
    }

    public boolean isValidCoordinate(int x, int y) {
        return isValidWidth(x) && isValidHeight(y);
    }

    private boolean isValidHeight(int y) {
        return y >= 0 && y <= height;
    }

    private boolean isValidWidth(int x) {
        return x >= 0 && x <= width;
    }

    public boolean isAvailable(int x, int y) {
        for (final Probe probe : probes) {
            if (probe.getX() == x && probe.getY() == y) {

                return false;
            }
        }

        return true;
    }
}
