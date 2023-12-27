package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.ModoAcao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.ModoAcaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ModoAcaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.ModoAcaoMapper;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.ModoAcaoUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.ModoAcaoUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;

@Service
public class ModoAcaoUseCaseImpl implements ModoAcaoUseCase {

    private final ModoAcaoRepository grupoRepository;
    private final ModoAcaoMapper grupoMapper;
    private final ModoAcaoUseCaseValidateImpl grupoUseCaseValidate;

    public ModoAcaoUseCaseImpl(ModoAcaoRepository grupoRepository,
            ModoAcaoUseCaseValidateImpl grupoUseCaseValidate) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(ModoAcaoMapper.class);
        this.grupoUseCaseValidate = grupoUseCaseValidate;
    }

    @Override
    public ModoAcaoDTO insert(ModoAcaoDTO grupoDTO) {
        grupoUseCaseValidate.validateInsert(grupoDTO);

        if (grupoRepository.existsByNomeIgnoreCase(grupoDTO.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        ModoAcao grupo = grupoMapper.grupoDTOToModoAcao(grupoDTO);
        ModoAcao responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToModoAcaoDTO(responde);
    }

    @Override
    public ModoAcaoDTO update(ModoAcaoDTO grupoDTO) {

        grupoUseCaseValidate.validateUpdate(grupoDTO);

        if (!grupoRepository.existsById(grupoDTO.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = grupoRepository.findIdByNomeIgnoreCaseAndIdNot(grupoDTO.getNome(), grupoDTO.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        ModoAcao grupo = grupoMapper.grupoDTOToModoAcao(grupoDTO);
        ModoAcao responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToModoAcaoDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        grupoUseCaseValidate.validateDelete(id);

        if (!grupoRepository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        this.grupoRepository.deleteById(id);
        Optional<ModoAcao> deletedModoAcao = this.grupoRepository.findById(id);
        return deletedModoAcao.isEmpty();
    }

    @Override
    public ModoAcaoDTO getById(int id) {

        Optional<ModoAcao> grupo = grupoRepository.findById(id);

        if (!grupo.isPresent()) {
            throw new WithIdNotFoundException();
        }

        return grupoMapper.grupoToModoAcaoDTO(grupo.get());
    }

    @Override
    public List<ModoAcaoDTO> getAll() {
        List<ModoAcao> grupos = this.grupoRepository.findAll();
        return grupoMapper.listModoAcaoToListModoAcaoDTO(grupos);
    }

}
