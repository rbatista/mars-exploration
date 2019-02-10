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

    public static CardinalDirection fromMnemonic(final String mnemonic) {

        if (!CARDINAL_DIRECTION_BY_MNEMONIC.containsKey(mnemonic)) {

            throw new IllegalArgumentException("Cardinal Direction not supported");
        }

        return CARDINAL_DIRECTION_BY_MNEMONIC.get(mnemonic);
    }

}
