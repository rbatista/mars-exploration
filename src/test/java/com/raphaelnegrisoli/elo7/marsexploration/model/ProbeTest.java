package com.raphaelnegrisoli.elo7.marsexploration.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProbeTest {

    @Mock
    private Plain plain;

    @Test
    public void testMove_north() {

        when(plain.isValidCoordinate(5, 6)).thenReturn(true);
        when(plain.isAvailable(5, 6)).thenReturn(true);

        final Probe probe = new Probe(5, 5, CardinalDirection.NORTH, plain);
        probe.move();

        assertEquals(5, probe.getX());
        assertEquals(6, probe.getY());
    }

    @Test
    public void testMove_south() {

        when(plain.isValidCoordinate(5, 4)).thenReturn(true);
        when(plain.isAvailable(5, 4)).thenReturn(true);

        final Probe probe = new Probe(5, 5, CardinalDirection.SOUTH, plain);
        probe.move();

        assertEquals(5, probe.getX());
        assertEquals(4, probe.getY());
    }

    @Test
    public void testMove_east() {

        when(plain.isValidCoordinate(6, 5)).thenReturn(true);
        when(plain.isAvailable(6, 5)).thenReturn(true);

        final Probe probe = new Probe(5, 5, CardinalDirection.EAST, plain);
        probe.move();

        assertEquals(6, probe.getX());
        assertEquals(5, probe.getY());
    }

    @Test
    public void testMove_west() {

        when(plain.isValidCoordinate(4, 5)).thenReturn(true);
        when(plain.isAvailable(4, 5)).thenReturn(true);

        final Probe probe = new Probe(5, 5, CardinalDirection.WEST, plain);
        probe.move();

        assertEquals(4, probe.getX());
        assertEquals(5, probe.getY());
    }

    @Test(expected = IllegalStateException.class)
    public void testMove_invalidCoordinate() {

        when(plain.isValidCoordinate(5, 6)).thenReturn(false);
        when(plain.isAvailable(5, 6)).thenReturn(true);

        final Probe probe = new Probe(5, 5, CardinalDirection.NORTH, plain);
        probe.move();

        fail("Invalid coordinate should throw an exception");
    }

    @Test(expected = IllegalStateException.class)
    public void testMove_unavailableCoordinate() {

        when(plain.isValidCoordinate(5, 6)).thenReturn(true);
        when(plain.isAvailable(5, 6)).thenReturn(false);

        final Probe probe = new Probe(5, 5, CardinalDirection.NORTH, plain);
        probe.move();

        fail("Unavailable coordinate should throw an exception");
    }
}