package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoInfluenciaSolo;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoInfluenciaSoloRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoInfluenciaSoloDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.TipoInfluenciaSoloMapper;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.TipoInfluenciaSoloUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.TipoInfluenciaSoloUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;

@Service
public class TipoInfluenciaSoloUseCaseImpl implements TipoInfluenciaSoloUseCase {

    private final TipoInfluenciaSoloRepository grupoRepository;
    private final TipoInfluenciaSoloMapper grupoMapper;
    private final TipoInfluenciaSoloUseCaseValidateImpl grupoUseCaseValidate;

    public TipoInfluenciaSoloUseCaseImpl(TipoInfluenciaSoloRepository grupoRepository,
            TipoInfluenciaSoloUseCaseValidateImpl grupoUseCaseValidate) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(TipoInfluenciaSoloMapper.class);
        this.grupoUseCaseValidate = grupoUseCaseValidate;
    }

    @Override
    public TipoInfluenciaSoloDTO insert(TipoInfluenciaSoloDTO grupoDTO) {
        grupoUseCaseValidate.validateInsert(grupoDTO);

        if (grupoRepository.existsByNomeIgnoreCase(grupoDTO.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        TipoInfluenciaSolo grupo = grupoMapper.grupoDTOToTipoInfluenciaSolo(grupoDTO);
        TipoInfluenciaSolo responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToTipoInfluenciaSoloDTO(responde);
    }

    @Override
    public TipoInfluenciaSoloDTO update(TipoInfluenciaSoloDTO grupoDTO) {

        grupoUseCaseValidate.validateUpdate(grupoDTO);

        if (!grupoRepository.existsById(grupoDTO.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = grupoRepository.findIdByNomeIgnoreCaseAndIdNot(grupoDTO.getNome(), grupoDTO.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        TipoInfluenciaSolo grupo = grupoMapper.grupoDTOToTipoInfluenciaSolo(grupoDTO);
        TipoInfluenciaSolo responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToTipoInfluenciaSoloDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        grupoUseCaseValidate.validateDelete(id);

        if (!grupoRepository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        this.grupoRepository.deleteById(id);
        Optional<TipoInfluenciaSolo> deletedTipoInfluenciaSolo = this.grupoRepository.findById(id);
        return deletedTipoInfluenciaSolo.isEmpty();
    }

    @Override
    public TipoInfluenciaSoloDTO getById(int id) {

        Optional<TipoInfluenciaSolo> grupo = grupoRepository.findById(id);

        if (!grupo.isPresent()) {
            throw new WithIdNotFoundException();
        }

        return grupoMapper.grupoToTipoInfluenciaSoloDTO(grupo.get());
    }

    @Override
    public List<TipoInfluenciaSoloDTO> getAll() {
        List<TipoInfluenciaSolo> grupos = this.grupoRepository.findAll();
        return grupoMapper.listTipoInfluenciaSoloToListTipoInfluenciaSoloDTO(grupos);
    }

}
