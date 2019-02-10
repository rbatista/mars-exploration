package com.raphaelnegrisoli.elo7.marsexploration.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum CardinalDirection {

    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    private static final Map<String, CardinalDirection> CARDINAL_DIRECTION_BY_MNEMONIC =
            new HashMap<>();

    static {

        Arrays.stream(values()).forEach(value ->
                CARDINAL_DIRECTION_BY_MNEMONIC.put(value.getMnemonic(), value));
    }

    private final String mnemonic;

    CardinalDirection(final String mnemonic) {

        this.mnemonic = mnemonic;
    }

    public String getMnemonic() {

        return mnemonic;
    }

    public CardinalDirection getLeftDirection() {

        final CardinalDirection[] directions = values();
        int indexAtLeftDirection = (directions.length + ordinal() - 1) % directions.length;
        return directions[indexAtLeftDirection];
    }

    public CardinalDirection getRightDirection() {

        final CardinalDirection[] directions = values();
        int indexAtRightDirection = (ordinal() + 1) % directions.length;
        return directions[indexAtRightDirection];
    }

    public static CardinalDirection fromMnemonic(final String mnemonic) {

        if (!CARDINAL_DIRECTION_BY_MNEMONIC.containsKey(mnemonic)) {

            throw new IllegalArgumentException("Cardinal Direction not supported");
        }

        return CARDINAL_DIRECTION_BY_MNEMONIC.get(mnemonic);
    }

}
