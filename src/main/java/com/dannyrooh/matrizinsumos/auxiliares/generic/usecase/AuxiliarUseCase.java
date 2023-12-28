package com.dannyrooh.matrizinsumos.auxiliares.generic.usecase;

import java.util.List;

public interface AuxiliarUseCase<D, ID> {

    public D insert(D auxiliar);

    public D update(D auxiliar);

    public Boolean delete(ID id);

    public D getById(ID id);

    public List<D> getAll();

}
