package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase;

import java.util.List;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.PlantaDaninhaDTO;

public interface PlantaDaninhaUseCase {

    public PlantaDaninhaDTO insert(PlantaDaninhaDTO grupo);

    public PlantaDaninhaDTO update(PlantaDaninhaDTO grupo);

    public Boolean delete(int id);

    public PlantaDaninhaDTO getById(int id);

    public List<PlantaDaninhaDTO> getAll();

}
