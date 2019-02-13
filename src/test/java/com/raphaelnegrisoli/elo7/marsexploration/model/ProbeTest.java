package com.raphaelnegrisoli.elo7.marsexploration.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProbeTest {

    @Mock
    private Plateau plateau;

    @Test
    public void testMove_north() {

        when(plateau.isValidCoordinate(5, 6)).thenReturn(true);
        when(plateau.isCoordinateAvailable(5, 6)).thenReturn(true);

        final Probe probe = new Probe(5, 5, CardinalDirection.NORTH, plateau);
        probe.move();

        assertEquals((Integer) 5, probe.getLongitude());
        assertEquals((Integer) 6, probe.getLatitude());
    }

    @Test
    public void testMove_south() {

        when(plateau.isValidCoordinate(5, 4)).thenReturn(true);
        when(plateau.isCoordinateAvailable(5, 4)).thenReturn(true);

        final Probe probe = new Probe(5, 5, CardinalDirection.SOUTH, plateau);
        probe.move();

        assertEquals((Integer) 5, probe.getLongitude());
        assertEquals((Integer) 4, probe.getLatitude());
    }

    @Test
    public void testMove_east() {

        when(plateau.isValidCoordinate(6, 5)).thenReturn(true);
        when(plateau.isCoordinateAvailable(6, 5)).thenReturn(true);

        final Probe probe = new Probe(5, 5, CardinalDirection.EAST, plateau);
        probe.move();

        assertEquals((Integer) 6, probe.getLongitude());
        assertEquals((Integer) 5, probe.getLatitude());
    }

    @Test
    public void testMove_west() {

        when(plateau.isValidCoordinate(4, 5)).thenReturn(true);
        when(plateau.isCoordinateAvailable(4, 5)).thenReturn(true);

        final Probe probe = new Probe(5, 5, CardinalDirection.WEST, plateau);
        probe.move();

        assertEquals((Integer) 4, probe.getLongitude());
        assertEquals((Integer) 5, probe.getLatitude());
    }

    @Test(expected = IllegalStateException.class)
    public void testMove_invalidCoordinate() {

        when(plateau.isValidCoordinate(5, 6)).thenReturn(false);

        final Probe probe = new Probe(5, 5, CardinalDirection.NORTH, plateau);
        probe.move();

        fail("Invalid coordinate should throw an exception");
    }

    @Test(expected = IllegalStateException.class)
    public void testMove_unavailableCoordinate() {

        when(plateau.isValidCoordinate(5, 6)).thenReturn(true);
        when(plateau.isCoordinateAvailable(5, 6)).thenReturn(false);

        final Probe probe = new Probe(5, 5, CardinalDirection.NORTH, plateau);
        probe.move();

        fail("Unavailable coordinate should throw an exception");
    }

    @Test
    public void testExecuteCommand_turnLeft() {

        final Probe probe = new Probe(5, 5, CardinalDirection.NORTH, plateau);
        probe.executeCommand(ProbeCommand.TURN_LEFT);

        assertEquals(CardinalDirection.WEST, probe.getCurrentDirection());
        assertEquals((Integer) 5, probe.getLongitude());
        assertEquals((Integer) 5, probe.getLatitude());
    }

    @Test
    public void testExecuteCommand_turnRight() {

        final Probe probe = new Probe(5, 5, CardinalDirection.NORTH, plateau);
        probe.executeCommand(ProbeCommand.TURN_RIGHT);

        assertEquals(CardinalDirection.EAST, probe.getCurrentDirection());
        assertEquals((Integer) 5, probe.getLongitude());
        assertEquals((Integer) 5, probe.getLatitude());
    }

    @Test
    public void testExecuteCommand_turnMove() {

        when(plateau.isValidCoordinate(5, 6)).thenReturn(true);
        when(plateau.isCoordinateAvailable(5, 6)).thenReturn(true);

        final Probe probe = new Probe(5, 5, CardinalDirection.NORTH, plateau);
        probe.executeCommand(ProbeCommand.MOVE);

        assertEquals(CardinalDirection.NORTH, probe.getCurrentDirection());
        assertEquals((Integer) 5, probe.getLongitude());
        assertEquals((Integer) 6, probe.getLatitude());
    }

}