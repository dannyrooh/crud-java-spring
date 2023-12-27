package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase;

import java.util.List;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseProdutoDTO;

public interface ClasseProdutoUseCase {

    public ClasseProdutoDTO insert(ClasseProdutoDTO grupo);

    public ClasseProdutoDTO update(ClasseProdutoDTO grupo);

    public Boolean delete(int id);

    public ClasseProdutoDTO getById(int id);

    public List<ClasseProdutoDTO> getAll();

}
