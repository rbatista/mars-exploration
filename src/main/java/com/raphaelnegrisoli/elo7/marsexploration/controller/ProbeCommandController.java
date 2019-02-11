package com.raphaelnegrisoli.elo7.marsexploration.controller;

import com.raphaelnegrisoli.elo7.marsexploration.controller.dto.ProbeCommandDTO;
import com.raphaelnegrisoli.elo7.marsexploration.service.ProbeCommandService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("probe")
public class ProbeCommandController {

    private final ProbeCommandService probeCommandService;

    public ProbeCommandController(ProbeCommandService probeCommandService) {
        this.probeCommandService = probeCommandService;
    }

    @PostMapping("{probeId}/command")
    public ProbeCommandDTO executeCommand(@PathVariable final Integer probeId,
                                          @RequestBody final ProbeCommandDTO dto) {
        dto.setProbeId(probeId);
        probeCommandService.execute(dto);
        return dto;
    }

}
