package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.ClasseProduto;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.ClasseProdutoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseProdutoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.ClasseProdutoMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl.AuxiliarUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl.AuxiliarUseCaseValidateImpl;

@Service
public class ClasseProdutoUseCaseImpl extends
        AuxiliarUseCaseImpl<ClasseProdutoDTO, ClasseProduto, Integer, ClasseProdutoRepository, AuxiliarMapper<ClasseProduto, ClasseProdutoDTO>, AuxiliarUseCaseValidateImpl<ClasseProdutoDTO, Integer>> {

    public ClasseProdutoUseCaseImpl(ClasseProdutoRepository repository) {
        setRepository(repository);
        setMapper(Mappers.getMapper(ClasseProdutoMapper.class));
        setValidate(new AuxiliarUseCaseValidateImpl<ClasseProdutoDTO, Integer>());
    }

}
