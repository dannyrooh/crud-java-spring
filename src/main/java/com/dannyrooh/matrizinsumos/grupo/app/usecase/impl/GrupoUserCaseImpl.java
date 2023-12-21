package com.dannyrooh.matrizinsumos.grupo.app.usecase.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.grupo.app.dto.GrupoDTO;
import com.dannyrooh.matrizinsumos.grupo.app.mapper.GrupoMapper;
import com.dannyrooh.matrizinsumos.grupo.app.usecase.GrupoUseCase;
import com.dannyrooh.matrizinsumos.grupo.domain.model.Grupo;
import com.dannyrooh.matrizinsumos.grupo.domain.repository.GrupoRepository;

@Service
public class GrupoUserCaseImpl implements GrupoUseCase {

    GrupoRepository grupoRepository;

    GrupoMapper grupoMapper;

    public GrupoUserCaseImpl(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(GrupoMapper.class);
    }

    @Override
    public GrupoDTO insert(GrupoDTO grupoDTO) {
        Grupo grupo = grupoMapper.grupoDTOToGrupo(grupoDTO);
        Grupo responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToGrupoDTO(responde);
    }

    @Override
    public GrupoDTO update(GrupoDTO grupoDTO) {
        Grupo grupo = grupoMapper.grupoDTOToGrupo(grupoDTO);
        Grupo responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToGrupoDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        this.grupoRepository.deleteById(id);
        Optional<Grupo> deletedGrupo = this.grupoRepository.findById(id);
        return deletedGrupo.isEmpty();
    }

    @Override
    public GrupoDTO getById(int id) {
        Grupo grupo = this.grupoRepository.getOne(id);
        return grupoMapper.grupoToGrupoDTO(grupo);
    }

    @Override
    public List<GrupoDTO> getAll() {
        List<Grupo> grupos = this.grupoRepository.findAll();
        return grupoMapper.listGrupoToListGrupoDTO(grupos);
        // List<GrupoDTO> lista = Arrays.asList(
        // new GrupoDTO(1, "John Doe"),
        // new GrupoDTO(2, "Mary Crazy"),
        // new GrupoDTO(3, "Patrick Hungry"),
        // new GrupoDTO(3, "Petter Malasar"));
        // return lista;
    }

}
