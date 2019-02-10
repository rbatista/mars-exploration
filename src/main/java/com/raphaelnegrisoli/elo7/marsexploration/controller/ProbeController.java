package com.raphaelnegrisoli.elo7.marsexploration.controller;

import com.raphaelnegrisoli.elo7.marsexploration.controller.adapter.ProbeAdapter;
import com.raphaelnegrisoli.elo7.marsexploration.controller.dto.ProbeDTO;
import com.raphaelnegrisoli.elo7.marsexploration.model.Probe;
import com.raphaelnegrisoli.elo7.marsexploration.service.ProbeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("probe")
public class ProbeController {

    private final ProbeAdapter probeAdapter;

    private final ProbeService probeService;

    public ProbeController(final ProbeAdapter probeAdapter,
                           final ProbeService probeService) {
        this.probeAdapter = probeAdapter;
        this.probeService = probeService;
    }

    @PostMapping("/")
    public ProbeDTO create(@Valid @RequestBody final ProbeDTO dto) {
        final Probe request = probeAdapter.adapt(dto);
        final Probe response = probeService.create(request);
        return probeAdapter.adapt(response);
    }

    @GetMapping("/{id}")
    public ProbeDTO findById(@PathVariable final Integer id) {
        return probeAdapter.adapt(probeService.find(id));
    }

}
