package com.dannyrooh.matrizinsumos.auxiliares.domain.dto;

import com.dannyrooh.matrizinsumos.auxiliares.generic.dto.AuxiliarDTO;

public class TipoSoloDTO extends AuxiliarDTO {

    public TipoSoloDTO(int id, String nome) {
        super(id, nome);
    }

    @Override
    public int getNameSize() {
        return 50;
    }

}
