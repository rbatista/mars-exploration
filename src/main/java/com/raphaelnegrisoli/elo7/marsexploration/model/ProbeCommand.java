package com.raphaelnegrisoli.elo7.marsexploration.model;


public enum ProbeCommand {

    TURN_LEFT("L"),
    TURN_RIGHT("R"),
    MOVE("M");

    private final String mnemonic;

    ProbeCommand(final String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getMnemonic() {
        return mnemonic;
    }
}
