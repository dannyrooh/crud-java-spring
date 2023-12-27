package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.Atuacao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.AtuacaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.AtuacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.AtuacaoMapper;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.AtuacaoUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.AtuacaoUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;

@Service
public class AtuacaoUseCaseImpl implements AtuacaoUseCase {

    private final AtuacaoRepository atuacaoRepository;
    private final AtuacaoMapper atuacaoMapper;
    private final AtuacaoUseCaseValidateImpl atuacaoUseCaseValidate;

    public AtuacaoUseCaseImpl(AtuacaoRepository atuacaoRepository,
            AtuacaoUseCaseValidateImpl atuacaoUseCaseValidate) {
        this.atuacaoRepository = atuacaoRepository;
        this.atuacaoMapper = Mappers.getMapper(AtuacaoMapper.class);
        this.atuacaoUseCaseValidate = atuacaoUseCaseValidate;
    }

    @Override
    public AtuacaoDTO insert(AtuacaoDTO atuacaoDTO) {
        atuacaoUseCaseValidate.validateInsert(atuacaoDTO);

        if (atuacaoRepository.existsByNomeIgnoreCase(atuacaoDTO.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        Atuacao atuacao = atuacaoMapper.atuacaoDTOToAtuacao(atuacaoDTO);
        Atuacao responde = this.atuacaoRepository.save(atuacao);
        return atuacaoMapper.atuacaoToAtuacaoDTO(responde);
    }

    @Override
    public AtuacaoDTO update(AtuacaoDTO atuacaoDTO) {

        atuacaoUseCaseValidate.validateUpdate(atuacaoDTO);

        if (!atuacaoRepository.existsById(atuacaoDTO.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = atuacaoRepository.findIdByNomeIgnoreCaseAndIdNot(atuacaoDTO.getNome(), atuacaoDTO.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        Atuacao atuacao = atuacaoMapper.atuacaoDTOToAtuacao(atuacaoDTO);
        Atuacao responde = this.atuacaoRepository.save(atuacao);
        return atuacaoMapper.atuacaoToAtuacaoDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        atuacaoUseCaseValidate.validateDelete(id);

        if (!atuacaoRepository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        this.atuacaoRepository.deleteById(id);
        Optional<Atuacao> deletedAtuacao = this.atuacaoRepository.findById(id);
        return deletedAtuacao.isEmpty();
    }

    @Override
    public AtuacaoDTO getById(int id) {

        Optional<Atuacao> atuacao = atuacaoRepository.findById(id);

        if (!atuacao.isPresent()) {
            throw new WithIdNotFoundException();
        }

        return atuacaoMapper.atuacaoToAtuacaoDTO(atuacao.get());
    }

    @Override
    public List<AtuacaoDTO> getAll() {
        List<Atuacao> atuacaos = this.atuacaoRepository.findAll();
        return atuacaoMapper.listAtuacaoToListAtuacaoDTO(atuacaos);
    }

}
