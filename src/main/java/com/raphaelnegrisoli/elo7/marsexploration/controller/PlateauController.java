package com.raphaelnegrisoli.elo7.marsexploration.controller;

import com.raphaelnegrisoli.elo7.marsexploration.controller.adapter.PlateauAdapter;
import com.raphaelnegrisoli.elo7.marsexploration.controller.dto.PlateauDTO;
import com.raphaelnegrisoli.elo7.marsexploration.model.Plateau;
import com.raphaelnegrisoli.elo7.marsexploration.service.PlateauService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("plateau")
public class PlateauController {

    private final PlateauAdapter plateauAdapter;
    private final PlateauService plateauService;

    public PlateauController(final PlateauAdapter plateauAdapter, final PlateauService plateauService) {
        this.plateauAdapter = plateauAdapter;
        this.plateauService = plateauService;
    }

    @PostMapping
    public PlateauDTO create(@Valid @RequestBody final PlateauDTO dto) {
        final Plateau request = plateauAdapter.adapt(dto);
        final Plateau response = plateauService.save(request);
        return plateauAdapter.adapt(response);
    }

    @GetMapping("/{id}")
    public PlateauDTO findById(@PathVariable final Integer id) {
        return plateauAdapter.adapt(plateauService.find(id));
    }
}
