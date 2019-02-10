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
    public void fromMnemonic(final String mnemonic, final CardinalDirection expected) {

        final CardinalDirection result = CardinalDirection.fromMnemonic(mnemonic);
        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromMnemonic_throwsExceptionOnInvalidValue() {

        final String invalidDirection = "X";
        CardinalDirection.fromMnemonic(invalidDirection);
        Assert.fail("An invalid direction should throw exception");
    }

}
