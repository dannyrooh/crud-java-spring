package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.ClasseProduto;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseProdutoDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClasseProdutoMapper {

    ClasseProduto grupoDTOToClasseProduto(ClasseProdutoDTO grupoDto);

    ClasseProdutoDTO grupoToClasseProdutoDTO(ClasseProduto grupo);

    List<ClasseProduto> listClasseProdutoDTOToListClasseProduto(List<ClasseProdutoDTO> grupoDTOList);

    List<ClasseProdutoDTO> listClasseProdutoToListClasseProdutoDTO(List<ClasseProduto> grupoList);

}
