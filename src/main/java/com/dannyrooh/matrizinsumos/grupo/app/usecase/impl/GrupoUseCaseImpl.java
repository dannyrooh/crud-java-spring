package com.dannyrooh.matrizinsumos.grupo.app.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.exception.IdNotFoundException;
import com.dannyrooh.matrizinsumos.grupo.app.dto.GrupoDTO;
import com.dannyrooh.matrizinsumos.grupo.app.mapper.GrupoMapper;
import com.dannyrooh.matrizinsumos.grupo.app.usecase.GrupoUseCase;
import com.dannyrooh.matrizinsumos.grupo.domain.model.Grupo;
import com.dannyrooh.matrizinsumos.grupo.domain.repository.GrupoRepository;
import com.dannyrooh.matrizinsumos.util.ValidatorFieldsUtil;

@Service
public class GrupoUseCaseImpl implements GrupoUseCase {

    private final GrupoRepository grupoRepository;
    private final GrupoMapper grupoMapper;

    public GrupoUseCaseImpl(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(GrupoMapper.class);
    }

    private Grupo getGrupo(Integer id) {
        Optional<Grupo> grupo = this.grupoRepository.findById(id);
        if (!grupo.isPresent()) {
            new IdNotFoundException();
        }
        return grupo.get();
    }

    @Override
    public GrupoDTO insert(GrupoDTO grupoDTO) {

        Grupo grupo = grupoMapper.grupoDTOToGrupo(grupoDTO);
        Grupo responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToGrupoDTO(responde);
    }

    @Override
    public GrupoDTO update(GrupoDTO grupoDTO) {

        ValidatorFieldsUtil.validateID(grupoDTO.getId());

        Grupo grupo = grupoMapper.grupoDTOToGrupo(grupoDTO);
        Grupo responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToGrupoDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        getGrupo(id);

        this.grupoRepository.deleteById(id);
        Optional<Grupo> deletedGrupo = this.grupoRepository.findById(id);
        return deletedGrupo.isEmpty();
    }

    @Override
    public GrupoDTO getById(int id) {
        return grupoMapper.grupoToGrupoDTO(this.getGrupo(id));
    }

    @Override
    public List<GrupoDTO> getAll() {
        List<Grupo> grupos = this.grupoRepository.findAll();
        return grupoMapper.listGrupoToListGrupoDTO(grupos);
    }

}
