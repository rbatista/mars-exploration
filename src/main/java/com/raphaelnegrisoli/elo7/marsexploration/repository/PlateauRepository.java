package com.raphaelnegrisoli.elo7.marsexploration.repository;

import com.raphaelnegrisoli.elo7.marsexploration.model.Plateau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlateauRepository extends JpaRepository<Plateau, Integer> {

}
