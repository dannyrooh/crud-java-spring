package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoLixiviacao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoLixiviacaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoLixiviacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.TipoLixiviacaoMapper;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.TipoLixiviacaoUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.TipoLixiviacaoUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;

@Service
public class TipoLixiviacaoUseCaseImpl implements TipoLixiviacaoUseCase {

    private final TipoLixiviacaoRepository grupoRepository;
    private final TipoLixiviacaoMapper grupoMapper;
    private final TipoLixiviacaoUseCaseValidateImpl grupoUseCaseValidate;

    public TipoLixiviacaoUseCaseImpl(TipoLixiviacaoRepository grupoRepository,
            TipoLixiviacaoUseCaseValidateImpl grupoUseCaseValidate) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(TipoLixiviacaoMapper.class);
        this.grupoUseCaseValidate = grupoUseCaseValidate;
    }

    @Override
    public TipoLixiviacaoDTO insert(TipoLixiviacaoDTO grupoDTO) {
        grupoUseCaseValidate.validateInsert(grupoDTO);

        if (grupoRepository.existsByNomeIgnoreCase(grupoDTO.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        TipoLixiviacao grupo = grupoMapper.grupoDTOToTipoLixiviacao(grupoDTO);
        TipoLixiviacao responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToTipoLixiviacaoDTO(responde);
    }

    @Override
    public TipoLixiviacaoDTO update(TipoLixiviacaoDTO grupoDTO) {

        grupoUseCaseValidate.validateUpdate(grupoDTO);

        if (!grupoRepository.existsById(grupoDTO.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = grupoRepository.findIdByNomeIgnoreCaseAndIdNot(grupoDTO.getNome(), grupoDTO.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        TipoLixiviacao grupo = grupoMapper.grupoDTOToTipoLixiviacao(grupoDTO);
        TipoLixiviacao responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToTipoLixiviacaoDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        grupoUseCaseValidate.validateDelete(id);

        if (!grupoRepository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        this.grupoRepository.deleteById(id);
        Optional<TipoLixiviacao> deletedTipoLixiviacao = this.grupoRepository.findById(id);
        return deletedTipoLixiviacao.isEmpty();
    }

    @Override
    public TipoLixiviacaoDTO getById(int id) {

        Optional<TipoLixiviacao> grupo = grupoRepository.findById(id);

        if (!grupo.isPresent()) {
            throw new WithIdNotFoundException();
        }

        return grupoMapper.grupoToTipoLixiviacaoDTO(grupo.get());
    }

    @Override
    public List<TipoLixiviacaoDTO> getAll() {
        List<TipoLixiviacao> grupos = this.grupoRepository.findAll();
        return grupoMapper.listTipoLixiviacaoToListTipoLixiviacaoDTO(grupos);
    }

}
