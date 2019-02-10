package com.raphaelnegrisoli.elo7.marsexploration.service;

import com.raphaelnegrisoli.elo7.marsexploration.model.Plateau;
import com.raphaelnegrisoli.elo7.marsexploration.repository.PlateauRepository;
import org.springframework.stereotype.Service;

@Service
public class PlateauService {

    private final PlateauRepository plateauRepository;

    public PlateauService(final PlateauRepository plateauRepository) {
        this.plateauRepository = plateauRepository;
    }

    public Plateau create(final Plateau plateau) {
        return plateauRepository.save(plateau);
    }

    public Plateau find(final Integer id) {
        return plateauRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plateau not found"));
    }
}
