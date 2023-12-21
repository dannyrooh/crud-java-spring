package com.dannyrooh.matrizinsumos.grupo.app.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.grupo.app.dto.GrupoDTO;
import com.dannyrooh.matrizinsumos.grupo.app.mapper.GrupoMapper;
import com.dannyrooh.matrizinsumos.grupo.app.usecase.GrupoUseCase;
import com.dannyrooh.matrizinsumos.grupo.domain.model.Grupo;
import com.dannyrooh.matrizinsumos.grupo.domain.repository.GrupoRepository;

@Service
public class GrupoUserCaseImpl implements GrupoUseCase {

    private final GrupoRepository grupoRepository;

    private final GrupoMapper grupoMapper;

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
        try {
            this.grupoRepository.deleteById(id);
            Optional<Grupo> deletedGrupo = this.grupoRepository.findById(id);
            return deletedGrupo.isEmpty();
        } catch (EmptyResultDataAccessException e) {
            return true;
        }

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
    }

}
