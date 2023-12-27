package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoPersistencia;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoPersistenciaRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoPersistenciaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.TipoPersistenciaMapper;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.TipoPersistenciaUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.TipoPersistenciaUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;

@Service
public class TipoPersistenciaUseCaseImpl implements TipoPersistenciaUseCase {

    private final TipoPersistenciaRepository grupoRepository;
    private final TipoPersistenciaMapper grupoMapper;
    private final TipoPersistenciaUseCaseValidateImpl grupoUseCaseValidate;

    public TipoPersistenciaUseCaseImpl(TipoPersistenciaRepository grupoRepository,
            TipoPersistenciaUseCaseValidateImpl grupoUseCaseValidate) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(TipoPersistenciaMapper.class);
        this.grupoUseCaseValidate = grupoUseCaseValidate;
    }

    @Override
    public TipoPersistenciaDTO insert(TipoPersistenciaDTO grupoDTO) {
        grupoUseCaseValidate.validateInsert(grupoDTO);

        if (grupoRepository.existsByNomeIgnoreCase(grupoDTO.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        TipoPersistencia grupo = grupoMapper.grupoDTOToTipoPersistencia(grupoDTO);
        TipoPersistencia responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToTipoPersistenciaDTO(responde);
    }

    @Override
    public TipoPersistenciaDTO update(TipoPersistenciaDTO grupoDTO) {

        grupoUseCaseValidate.validateUpdate(grupoDTO);

        if (!grupoRepository.existsById(grupoDTO.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = grupoRepository.findIdByNomeIgnoreCaseAndIdNot(grupoDTO.getNome(), grupoDTO.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        TipoPersistencia grupo = grupoMapper.grupoDTOToTipoPersistencia(grupoDTO);
        TipoPersistencia responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToTipoPersistenciaDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        grupoUseCaseValidate.validateDelete(id);

        if (!grupoRepository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        this.grupoRepository.deleteById(id);
        Optional<TipoPersistencia> deletedTipoPersistencia = this.grupoRepository.findById(id);
        return deletedTipoPersistencia.isEmpty();
    }

    @Override
    public TipoPersistenciaDTO getById(int id) {

        Optional<TipoPersistencia> grupo = grupoRepository.findById(id);

        if (!grupo.isPresent()) {
            throw new WithIdNotFoundException();
        }

        return grupoMapper.grupoToTipoPersistenciaDTO(grupo.get());
    }

    @Override
    public List<TipoPersistenciaDTO> getAll() {
        List<TipoPersistencia> grupos = this.grupoRepository.findAll();
        return grupoMapper.listTipoPersistenciaToListTipoPersistenciaDTO(grupos);
    }

}
