package com.raphaelnegrisoli.elo7.marsexploration.service;

import com.raphaelnegrisoli.elo7.marsexploration.controller.dto.ProbeCommandDTO;
import com.raphaelnegrisoli.elo7.marsexploration.model.Probe;
import com.raphaelnegrisoli.elo7.marsexploration.model.ProbeCommand;
import org.springframework.stereotype.Service;

@Service
public class ProbeCommandService {

    private final ProbeService probeService;

    public ProbeCommandService(final ProbeService probeService) {
        this.probeService = probeService;
    }

    public synchronized void execute(final ProbeCommandDTO dto) {
        final Probe probe = probeService.find(dto.getProbeId());
        final ProbeCommand command = dto.getCommand();
        command.execute(probe);
        probeService.save(probe);
    }

}
