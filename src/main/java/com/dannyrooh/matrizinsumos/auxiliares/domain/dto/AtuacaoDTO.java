package com.dannyrooh.matrizinsumos.auxiliares.domain.dto;

import com.dannyrooh.matrizinsumos.auxiliares.generic.dto.AuxiliarDTO;

public class AtuacaoDTO extends AuxiliarDTO {

    public AtuacaoDTO(int id, String nome) {
        super(id, nome);
    }

    @Override
    public int getNameSize() {
        return 50;
    }

}
