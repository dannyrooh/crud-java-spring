package com.dannyrooh.matrizinsumos.auxiliares.generic.mapper;

import java.util.List;

public interface AuxiliarMapper<M, D> {

    M toModel(D dto);

    D toDTO(M model);

    List<M> toListModel(List<D> dtoList);

    List<D> toListDTO(List<M> modelList);

}
