package com.raphaelnegrisoli.elo7.marsexploration.controller.dto;

import com.raphaelnegrisoli.elo7.marsexploration.model.ProbeCommand;

public class ProbeCommandDTO {

    private Integer probeId;

    private ProbeCommand command;

    public Integer getProbeId() {
        return probeId;
    }

    public void setProbeId(final Integer probeId) {
        this.probeId = probeId;
    }

    public ProbeCommand getCommand() {
        return command;
    }

    public void setCommand(final ProbeCommand command) {
        this.command = command;
    }

}
