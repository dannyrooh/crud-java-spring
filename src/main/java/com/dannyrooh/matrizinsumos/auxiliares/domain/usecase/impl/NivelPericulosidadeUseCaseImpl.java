package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.NivelPericulosidade;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.NivelPericulosidadeRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.NivelPericulosidadeDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.NivelPericulosidadeMapper;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.NivelPericulosidadeUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.NivelPericulosidadeUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;

@Service
public class NivelPericulosidadeUseCaseImpl implements NivelPericulosidadeUseCase {

    private final NivelPericulosidadeRepository grupoRepository;
    private final NivelPericulosidadeMapper grupoMapper;
    private final NivelPericulosidadeUseCaseValidateImpl grupoUseCaseValidate;

    public NivelPericulosidadeUseCaseImpl(NivelPericulosidadeRepository grupoRepository,
            NivelPericulosidadeUseCaseValidateImpl grupoUseCaseValidate) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(NivelPericulosidadeMapper.class);
        this.grupoUseCaseValidate = grupoUseCaseValidate;
    }

    @Override
    public NivelPericulosidadeDTO insert(NivelPericulosidadeDTO grupoDTO) {
        grupoUseCaseValidate.validateInsert(grupoDTO);

        if (grupoRepository.existsByNomeIgnoreCase(grupoDTO.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        NivelPericulosidade grupo = grupoMapper.grupoDTOToNivelPericulosidade(grupoDTO);
        NivelPericulosidade responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToNivelPericulosidadeDTO(responde);
    }

    @Override
    public NivelPericulosidadeDTO update(NivelPericulosidadeDTO grupoDTO) {

        grupoUseCaseValidate.validateUpdate(grupoDTO);

        if (!grupoRepository.existsById(grupoDTO.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = grupoRepository.findIdByNomeIgnoreCaseAndIdNot(grupoDTO.getNome(), grupoDTO.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        NivelPericulosidade grupo = grupoMapper.grupoDTOToNivelPericulosidade(grupoDTO);
        NivelPericulosidade responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToNivelPericulosidadeDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        grupoUseCaseValidate.validateDelete(id);

        if (!grupoRepository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        this.grupoRepository.deleteById(id);
        Optional<NivelPericulosidade> deletedNivelPericulosidade = this.grupoRepository.findById(id);
        return deletedNivelPericulosidade.isEmpty();
    }

    @Override
    public NivelPericulosidadeDTO getById(int id) {

        Optional<NivelPericulosidade> grupo = grupoRepository.findById(id);

        if (!grupo.isPresent()) {
            throw new WithIdNotFoundException();
        }

        return grupoMapper.grupoToNivelPericulosidadeDTO(grupo.get());
    }

    @Override
    public List<NivelPericulosidadeDTO> getAll() {
        List<NivelPericulosidade> grupos = this.grupoRepository.findAll();
        return grupoMapper.listNivelPericulosidadeToListNivelPericulosidadeDTO(grupos);
    }

}
