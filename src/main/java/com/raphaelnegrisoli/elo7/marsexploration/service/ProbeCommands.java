package com.raphaelnegrisoli.elo7.marsexploration.service;

import com.raphaelnegrisoli.elo7.marsexploration.model.Probe;

import java.util.function.Consumer;

public class ProbeCommands {

    public Consumer<Probe> turnLeft() {
        return Probe::turnLeft;
    }

}
