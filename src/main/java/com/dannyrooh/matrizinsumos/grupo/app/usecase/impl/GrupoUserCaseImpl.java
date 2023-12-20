package com.dannyrooh.matrizinsumos.grupo.app.usecase.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.grupo.app.dto.GrupoDTO;
import com.dannyrooh.matrizinsumos.grupo.app.usecase.GrupoUseCase;

@Service
public class GrupoUserCaseImpl implements GrupoUseCase {

    @Override
    public GrupoDTO insert(GrupoDTO grupo) {
        return new GrupoDTO(10, grupo.getNome());
    }

    @Override
    public GrupoDTO update(GrupoDTO grupo) {
        grupo.setId(grupo.getId() + 1);
        return grupo;
    }

    @Override
    public Boolean delete(int id) {
        return true;
    }

    @Override
    public GrupoDTO getById(int id) {
        GrupoDTO grupo = new GrupoDTO(id, "John Doe");
        return grupo;
    }

    @Override
    public List<GrupoDTO> getAll() {

        List<GrupoDTO> lista = Arrays.asList(
                new GrupoDTO(1, "John Doe"),
                new GrupoDTO(2, "Mary Crazy"),
                new GrupoDTO(3, "Patrick Hungry"),
                new GrupoDTO(3, "Petter Malasar"));

        return lista;
    }

}
