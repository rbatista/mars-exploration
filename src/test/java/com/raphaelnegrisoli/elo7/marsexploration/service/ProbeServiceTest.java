package com.raphaelnegrisoli.elo7.marsexploration.service;

import com.raphaelnegrisoli.elo7.marsexploration.model.Probe;
import com.raphaelnegrisoli.elo7.marsexploration.repository.ProbeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProbeServiceTest {

    @Mock
    private ProbeRepository probeRepositoryMock;

    @Mock
    private PlateauService plateauServiceMock;

    @Mock
    private Probe probeMock;

    @Mock
    private Probe anotherProbeMock;

    @Test
    public void testSave_onNewCoordinate() {

        when(probeMock.getLongitude()).thenReturn(0);
        when(probeMock.getLatitude()).thenReturn(42);
        when(probeRepositoryMock.findByLongitudeAndLatitude(0, 42))
                .thenReturn(Optional.empty());
        when(probeRepositoryMock.save(probeMock)).thenReturn(probeMock);

        final ProbeService service = new ProbeService(probeRepositoryMock, plateauServiceMock);
        final Probe result = service.save(probeMock);
        assertEquals(probeMock, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSave_withAnotherProbeAtSameCoordinate() {

        when(probeMock.getLongitude()).thenReturn(0);
        when(probeMock.getLatitude()).thenReturn(42);
        when(probeRepositoryMock.findByLongitudeAndLatitude(0, 42))
                .thenReturn(Optional.of(anotherProbeMock));
        when(probeMock.getId()).thenReturn(123);
        when(anotherProbeMock.getId()).thenReturn(321);

        final ProbeService service = new ProbeService(probeRepositoryMock, plateauServiceMock);
        service.save(probeMock);
        fail("Another probe with same coordinate should throw exception");
    }

    @Test
    public void testSave_sameProbe() {

        when(probeMock.getLongitude()).thenReturn(0);
        when(probeMock.getLatitude()).thenReturn(42);
        when(probeRepositoryMock.findByLongitudeAndLatitude(0, 42))
                .thenReturn(Optional.of(anotherProbeMock));
        when(probeMock.getId()).thenReturn(123);
        when(anotherProbeMock.getId()).thenReturn(123);
        when(probeRepositoryMock.save(probeMock)).thenReturn(probeMock);

        final ProbeService service = new ProbeService(probeRepositoryMock, plateauServiceMock);
        service.save(probeMock);

        final Probe result = service.save(probeMock);
        assertEquals(probeMock, result);
    }
}