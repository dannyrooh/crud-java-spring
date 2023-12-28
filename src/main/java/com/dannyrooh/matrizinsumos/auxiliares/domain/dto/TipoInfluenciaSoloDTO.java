package com.dannyrooh.matrizinsumos.auxiliares.domain.dto;

import com.dannyrooh.matrizinsumos.auxiliares.generic.dto.AuxiliarDTO;

public class TipoInfluenciaSoloDTO extends AuxiliarDTO {

    public TipoInfluenciaSoloDTO(int id, String nome) {
        super(id, nome);
    }

    @Override
    public int getNameSize() {
        return 50;
    }

}
