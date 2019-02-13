package com.raphaelnegrisoli.elo7.marsexploration.service;

import com.raphaelnegrisoli.elo7.marsexploration.controller.dto.ProbeCommandDTO;
import com.raphaelnegrisoli.elo7.marsexploration.model.Probe;
import com.raphaelnegrisoli.elo7.marsexploration.model.ProbeCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProbeCommandServiceTest {

    @Mock
    private ProbeService probeServiceMock;

    @Mock
    private Probe probeMock;

    @Test
    public void testExecute_callExecuteOnProbe() {

        when(probeServiceMock.find(1)).thenReturn(probeMock);
        when(probeServiceMock.save(probeMock)).thenReturn(probeMock);

        final ProbeCommandDTO dto = new ProbeCommandDTO();
        dto.setProbeId(1);
        dto.setCommand(ProbeCommand.TURN_LEFT);

        final ProbeCommandService service = new ProbeCommandService(probeServiceMock);
        service.execute(dto);

        verify(probeMock).executeCommand(ProbeCommand.TURN_LEFT);
    }

}