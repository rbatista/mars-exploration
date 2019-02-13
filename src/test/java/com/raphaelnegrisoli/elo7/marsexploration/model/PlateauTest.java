package com.raphaelnegrisoli.elo7.marsexploration.model;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(DataProviderRunner.class)
public class PlateauTest {

    @Mock
    private Probe probeMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @DataProvider
    public static Object[][] validCoordinateDataProvider() {

        return new Object[][] {
                { 0, 0, true },
                { 100, 100, true },
                { -1, 0, false },
                { 0, -1, false },
                { 101, 100, false },
                { 100, 101, false },
        };
    }

    @Test
    @UseDataProvider("validCoordinateDataProvider")
    public void testIsValidCoordinate(final Integer longitude, final Integer latitude,
                                  final Boolean expected) {

        final Plateau plateau = new Plateau();
        plateau.setWidth(100);
        plateau.setHeight(100);

        assertEquals(expected, plateau.isValidCoordinate(longitude, latitude));
    }

    @Test
    public void testIsCoordinateAvailable_falseWhenThereIsAMatchingProbe() {

        when(probeMock.coordinateMatch(42, 42)).thenReturn(true);

        final Plateau plateau = new Plateau();
        plateau.setWidth(100);
        plateau.setHeight(100);

        plateau.addProbe(probeMock);

        assertFalse(plateau.isCoordinateAvailable(42, 42));
    }

    @Test
    public void testIsCoordinateAvailable_falseWhenThereIsNotAMatchingProbe() {

        when(probeMock.coordinateMatch(42, 42)).thenReturn(false);

        final Plateau plateau = new Plateau();
        plateau.setWidth(100);
        plateau.setHeight(100);

        plateau.addProbe(probeMock);

        assertTrue(plateau.isCoordinateAvailable(42, 42));
    }

}