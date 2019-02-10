package com.raphaelnegrisoli.elo7.marsexploration.controller.adapter;

import com.raphaelnegrisoli.elo7.marsexploration.controller.dto.PlateauDTO;
import com.raphaelnegrisoli.elo7.marsexploration.model.Plateau;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class PlateauAdapter {

    private final MapperFactory mapperFactory;

    public PlateauAdapter() {

        mapperFactory = new DefaultMapperFactory.Builder()
                .build();
        mapperFactory.classMap(Plateau.class, PlateauDTO.class)
                .byDefault()
                .register();
    }

    public Plateau adapt(final PlateauDTO dto) {
        return mapperFactory.getMapperFacade(PlateauDTO.class, Plateau.class)
                .map(dto);
    }

    public PlateauDTO adapt(final Plateau plateau) {
        return mapperFactory.getMapperFacade(Plateau.class, PlateauDTO.class)
                .map(plateau);
    }
}
