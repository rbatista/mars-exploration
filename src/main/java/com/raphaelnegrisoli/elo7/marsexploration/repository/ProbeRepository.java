package com.raphaelnegrisoli.elo7.marsexploration.repository;

import com.raphaelnegrisoli.elo7.marsexploration.model.Probe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProbeRepository extends JpaRepository<Probe, Integer> {

    Optional<Probe> findByLongitudeAndLatitude(final Integer longitude, final Integer latitude);
}
