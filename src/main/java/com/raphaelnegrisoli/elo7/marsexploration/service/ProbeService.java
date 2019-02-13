package com.raphaelnegrisoli.elo7.marsexploration.service;

import com.raphaelnegrisoli.elo7.marsexploration.model.Plateau;
import com.raphaelnegrisoli.elo7.marsexploration.model.Probe;
import com.raphaelnegrisoli.elo7.marsexploration.repository.ProbeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProbeService {

    private final ProbeRepository probeRepository;

    private final PlateauService plateauService;

    public ProbeService(final ProbeRepository probeRepository,
                        final PlateauService plateauService) {
        this.probeRepository = probeRepository;
        this.plateauService = plateauService;
    }

    public Probe save(final Probe probe) {
//        loadPlateau(probe);
        checkCoordinates(probe);
        return probeRepository.save(probe);
    }

    private void checkCoordinates(final Probe probe) {
        final Integer longitude = probe.getLongitude();
        final Integer latitude = probe.getLatitude();
        final Optional<Probe> probeAtSamePosition = probeRepository.findByLongitudeAndLatitude(longitude,
                latitude);

        if (probeAtSamePosition.isPresent()) {
            final String errorMessage = String.format("Coordinate (%s, %s) already has another probe. Aborting the command",
                    longitude, latitude);
            probeAtSamePosition.map(Probe::getId)
                    .filter(id -> id.equals(probe.getId()))
                    .orElseThrow(() -> new IllegalArgumentException(errorMessage));
        }
    }

    private void loadPlateau(final Probe probe) {
        final Plateau plateau = probe.getPlateau();
        if (plateau != null && plateau.getId() != null) {
            final Plateau persisted = plateauService.find(plateau.getId());
            probe.setPlateau(persisted);
        }
    }

    public Probe find(final Integer id) {
        return probeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Probe not found"));
    }
}
