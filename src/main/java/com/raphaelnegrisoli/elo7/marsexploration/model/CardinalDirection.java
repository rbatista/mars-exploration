package com.raphaelnegrisoli.elo7.marsexploration.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum CardinalDirection {

    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    /** Copy values to optimize the safety-copy from the {@link #values()} method */
    private static final List<CardinalDirection> SORTED_DIRECTIONS = Arrays.stream(values())
            .collect(Collectors.toList());

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

        int indexAtLeftDirection = (SORTED_DIRECTIONS.size() + ordinal() - 1) % SORTED_DIRECTIONS.size();
        return SORTED_DIRECTIONS.get(indexAtLeftDirection);
    }

    public CardinalDirection getRightDirection() {

        int indexAtRightDirection = (ordinal() + 1) % SORTED_DIRECTIONS.size();
        return SORTED_DIRECTIONS.get(indexAtRightDirection);
    }

    public static CardinalDirection fromMnemonic(final String mnemonic) {

        if (!CARDINAL_DIRECTION_BY_MNEMONIC.containsKey(mnemonic)) {

            throw new IllegalArgumentException("Cardinal Direction not supported");
        }

        return CARDINAL_DIRECTION_BY_MNEMONIC.get(mnemonic);
    }

}
