package com.raphaelnegrisoli.elo7.marsexploration.model;

import java.util.function.Consumer;

public enum ProbeCommand {

    TURN_LEFT("L", Probe::turnLeft),
    TURN_RIGHT("R", Probe::turnRight),
    MOVE("M", Probe::move);

    private final String mnemonic;
    private final Consumer<Probe> commandConsumer;

    ProbeCommand(final String mnemonic, final Consumer<Probe> commandConsumer) {
        this.mnemonic = mnemonic;
        this.commandConsumer = commandConsumer;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void execute(final Probe probe) {
        commandConsumer.accept(probe);
    }
}
