package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoRiscoPotencial;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoRiscoPotencialRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoRiscoPotencialDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.TipoRiscoPotencialMapper;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.TipoRiscoPotencialUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.TipoRiscoPotencialUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;

@Service
public class TipoRiscoPotencialUseCaseImpl implements TipoRiscoPotencialUseCase {

    private final TipoRiscoPotencialRepository grupoRepository;
    private final TipoRiscoPotencialMapper grupoMapper;
    private final TipoRiscoPotencialUseCaseValidateImpl grupoUseCaseValidate;

    public TipoRiscoPotencialUseCaseImpl(TipoRiscoPotencialRepository grupoRepository,
            TipoRiscoPotencialUseCaseValidateImpl grupoUseCaseValidate) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(TipoRiscoPotencialMapper.class);
        this.grupoUseCaseValidate = grupoUseCaseValidate;
    }

    @Override
    public TipoRiscoPotencialDTO insert(TipoRiscoPotencialDTO grupoDTO) {
        grupoUseCaseValidate.validateInsert(grupoDTO);

        if (grupoRepository.existsByNomeIgnoreCase(grupoDTO.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        TipoRiscoPotencial grupo = grupoMapper.grupoDTOToTipoRiscoPotencial(grupoDTO);
        TipoRiscoPotencial responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToTipoRiscoPotencialDTO(responde);
    }

    @Override
    public TipoRiscoPotencialDTO update(TipoRiscoPotencialDTO grupoDTO) {

        grupoUseCaseValidate.validateUpdate(grupoDTO);

        if (!grupoRepository.existsById(grupoDTO.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = grupoRepository.findIdByNomeIgnoreCaseAndIdNot(grupoDTO.getNome(), grupoDTO.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        TipoRiscoPotencial grupo = grupoMapper.grupoDTOToTipoRiscoPotencial(grupoDTO);
        TipoRiscoPotencial responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToTipoRiscoPotencialDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        grupoUseCaseValidate.validateDelete(id);

        if (!grupoRepository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        this.grupoRepository.deleteById(id);
        Optional<TipoRiscoPotencial> deletedTipoRiscoPotencial = this.grupoRepository.findById(id);
        return deletedTipoRiscoPotencial.isEmpty();
    }

    @Override
    public TipoRiscoPotencialDTO getById(int id) {

        Optional<TipoRiscoPotencial> grupo = grupoRepository.findById(id);

        if (!grupo.isPresent()) {
            throw new WithIdNotFoundException();
        }

        return grupoMapper.grupoToTipoRiscoPotencialDTO(grupo.get());
    }

    @Override
    public List<TipoRiscoPotencialDTO> getAll() {
        List<TipoRiscoPotencial> grupos = this.grupoRepository.findAll();
        return grupoMapper.listTipoRiscoPotencialToListTipoRiscoPotencialDTO(grupos);
    }

}
