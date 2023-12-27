package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoSolo;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoSoloRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoSoloDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.TipoSoloMapper;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.TipoSoloUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.TipoSoloUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;

@Service
public class TipoSoloUseCaseImpl implements TipoSoloUseCase {

    private final TipoSoloRepository grupoRepository;
    private final TipoSoloMapper grupoMapper;
    private final TipoSoloUseCaseValidateImpl grupoUseCaseValidate;

    public TipoSoloUseCaseImpl(TipoSoloRepository grupoRepository,
            TipoSoloUseCaseValidateImpl grupoUseCaseValidate) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(TipoSoloMapper.class);
        this.grupoUseCaseValidate = grupoUseCaseValidate;
    }

    @Override
    public TipoSoloDTO insert(TipoSoloDTO grupoDTO) {
        grupoUseCaseValidate.validateInsert(grupoDTO);

        if (grupoRepository.existsByNomeIgnoreCase(grupoDTO.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        TipoSolo grupo = grupoMapper.grupoDTOToTipoSolo(grupoDTO);
        TipoSolo responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToTipoSoloDTO(responde);
    }

    @Override
    public TipoSoloDTO update(TipoSoloDTO grupoDTO) {

        grupoUseCaseValidate.validateUpdate(grupoDTO);

        if (!grupoRepository.existsById(grupoDTO.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = grupoRepository.findIdByNomeIgnoreCaseAndIdNot(grupoDTO.getNome(), grupoDTO.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        TipoSolo grupo = grupoMapper.grupoDTOToTipoSolo(grupoDTO);
        TipoSolo responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToTipoSoloDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        grupoUseCaseValidate.validateDelete(id);

        if (!grupoRepository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        this.grupoRepository.deleteById(id);
        Optional<TipoSolo> deletedTipoSolo = this.grupoRepository.findById(id);
        return deletedTipoSolo.isEmpty();
    }

    @Override
    public TipoSoloDTO getById(int id) {

        Optional<TipoSolo> grupo = grupoRepository.findById(id);

        if (!grupo.isPresent()) {
            throw new WithIdNotFoundException();
        }

        return grupoMapper.grupoToTipoSoloDTO(grupo.get());
    }

    @Override
    public List<TipoSoloDTO> getAll() {
        List<TipoSolo> grupos = this.grupoRepository.findAll();
        return grupoMapper.listTipoSoloToListTipoSoloDTO(grupos);
    }

}
