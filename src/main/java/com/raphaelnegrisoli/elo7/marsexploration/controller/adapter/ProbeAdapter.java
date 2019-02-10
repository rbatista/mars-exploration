package com.raphaelnegrisoli.elo7.marsexploration.controller.adapter;

import com.raphaelnegrisoli.elo7.marsexploration.controller.dto.ProbeDTO;
import com.raphaelnegrisoli.elo7.marsexploration.model.Probe;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class ProbeAdapter {

    private final MapperFactory mapperFactory;

    public ProbeAdapter() {
        mapperFactory = new DefaultMapperFactory.Builder()
                .build();
        mapperFactory.classMap(Probe.class, ProbeDTO.class)
            .byDefault()
            .field("plateau.id", "plateauId")
            .register();
    }

    public Probe adapt(final ProbeDTO dto) {
        return mapperFactory.getMapperFacade(ProbeDTO.class, Probe.class)
                .map(dto);
    }

    public ProbeDTO adapt(final Probe probe) {
        return mapperFactory.getMapperFacade(Probe.class, ProbeDTO.class)
                .map(probe);
    }

}
