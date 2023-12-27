package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase;

import java.util.List;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseToxicologiaDTO;

public interface ClasseToxicologiaUseCase {

    public ClasseToxicologiaDTO insert(ClasseToxicologiaDTO grupo);

    public ClasseToxicologiaDTO update(ClasseToxicologiaDTO grupo);

    public Boolean delete(int id);

    public ClasseToxicologiaDTO getById(int id);

    public List<ClasseToxicologiaDTO> getAll();

}
