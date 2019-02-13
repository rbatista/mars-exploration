package com.raphaelnegrisoli.elo7.marsexploration.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Plateau {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer width;

    private Integer height;

    @OneToMany(mappedBy = "plateau", cascade = CascadeType.ALL)
    private List<Probe> probes = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public synchronized void addProbe(final Probe probe) {
        probes.add(probe);
    }

    public boolean isValidCoordinate(final Integer x, final Integer y) {
        return isValidWidth(x) && isValidHeight(y);
    }

    private boolean isValidHeight(final Integer y) {
        return y >= 0 && y <= height;
    }

    private boolean isValidWidth(final Integer x) {
        return x >= 0 && x <= width;
    }

    public boolean isCoordinateAvailable(final Integer x, final Integer y) {
        for (final Probe probe : probes) {
            if (probe.getLongitude() == x && probe.getLatitude() == y) {

                return false;
            }
        }

        return true;
    }

}
