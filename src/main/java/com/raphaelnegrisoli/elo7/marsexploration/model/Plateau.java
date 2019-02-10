package com.raphaelnegrisoli.elo7.marsexploration.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Plateau {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private final int width;
    private final int height;

    @OneToMany(mappedBy = "plateau")
    private final List<Probe> probes;

    public Plateau(int width, int height) {
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

    public void addProbe(final Probe probe) {

        this.probes.add(probe);
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
