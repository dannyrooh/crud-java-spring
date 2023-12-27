package com.dannyrooh.matrizinsumos.auxiliares.domain.validate;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.PlantaDaninhaDTO;

public interface PlantaDaninhaUseCaseValidade {

    public void validateInsert(PlantaDaninhaDTO grupoDTO);

    public void validateUpdate(PlantaDaninhaDTO grupoDTO);

    public void validateDelete(Integer id);

}
