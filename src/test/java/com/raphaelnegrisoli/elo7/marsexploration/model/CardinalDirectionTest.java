package com.raphaelnegrisoli.elo7.marsexploration.model;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class CardinalDirectionTest {

    @DataProvider
    public static Object[][] fromMnemonicDataProvider() {

        return new Object[][] {
                { "N", CardinalDirection.NORTH },
                { "E", CardinalDirection.EAST },
                { "W", CardinalDirection.WEST },
                { "S", CardinalDirection.SOUTH }
        };
    }

    @Test
    @UseDataProvider("fromMnemonicDataProvider")
    public void testFromMnemonic(final String mnemonic, final CardinalDirection expected) {

        final CardinalDirection result = CardinalDirection.fromMnemonic(mnemonic);
        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromMnemonic_throwsExceptionOnInvalidValue() {

        final String invalidDirection = "X";
        CardinalDirection.fromMnemonic(invalidDirection);
        Assert.fail("An invalid direction should throw exception");
    }

    @DataProvider
    public static Object[][] leftDirectionDataProvider() {

        return new Object[][] {
                { CardinalDirection.NORTH, CardinalDirection.WEST },
                { CardinalDirection.WEST, CardinalDirection.SOUTH },
                { CardinalDirection.SOUTH, CardinalDirection.EAST },
                { CardinalDirection.EAST, CardinalDirection.NORTH },
        };
    }

    @Test
    @UseDataProvider("leftDirectionDataProvider")
    public void testRotateLeft(final CardinalDirection direction, final CardinalDirection expected) {

        assertEquals(expected, direction.getLeftDirection());
    }

    @DataProvider
    public static Object[][] rightDirectionDataProvider() {

        return new Object[][] {
                { CardinalDirection.NORTH, CardinalDirection.EAST },
                { CardinalDirection.EAST, CardinalDirection.SOUTH },
                { CardinalDirection.SOUTH, CardinalDirection.WEST },
                { CardinalDirection.WEST, CardinalDirection.NORTH },
        };
    }

    @Test
    @UseDataProvider("rightDirectionDataProvider")
    public void testRightDirection(final CardinalDirection direction, final CardinalDirection expected) {

        assertEquals(expected, direction.getRightDirection());
    }

}
