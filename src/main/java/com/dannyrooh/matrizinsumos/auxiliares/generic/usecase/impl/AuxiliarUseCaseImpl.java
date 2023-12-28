package com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl;

import java.util.List;
import java.util.Optional;

import com.dannyrooh.matrizinsumos.auxiliares.generic.dto.AuxiliarDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.repository.AuxiliarRepository;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.AuxiliarUseCaseValidate;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;

/*
* @D DTO
* @M Model
* @ID Tipo da chave do Model
* @R Repository
* @P Mapper
* @V Validate
*/
public class AuxiliarUseCaseImpl<D extends AuxiliarDTO, M, ID, R extends AuxiliarRepository<M, ID>, P extends AuxiliarMapper<M, D>, V extends AuxiliarUseCaseValidate<D, ID>>
        implements AuxiliarUseCase<D, ID> {

    private R repository;
    private P mapper;
    private V validate;

    protected void setRepository(R repository) {
        this.repository = repository;
    }

    protected void setMapper(P mapper) {
        this.mapper = mapper;
    }

    protected void setValidate(V validate) {
        this.validate = validate;
    }

    @Override
    public D insert(D dto) {
        validate.validateInsert(dto);

        if (repository.existsByNomeIgnoreCase(dto.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        M model = mapper.toModel(dto);
        M responde = repository.save(model);
        return mapper.toDTO(responde);

    }

    @Override
    public D update(D dto) {

        validate.validateUpdate(dto);

        if (!repository.existsById((ID)dto.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = (Integer) repository.findIdByNomeIgnoreCaseAndIdNot(dto.getNome(),
                (ID) dto.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        M model = mapper.toModel(dto);
        M responde = repository.save(model);
        return mapper.toDTO(responde);

    }

    @Override
    public Boolean delete(ID id) {
        validate.validateDelete(id);

        if (!repository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        repository.deleteById(id);
        Optional<M> deletedAtuacao = repository.findById(id);
        return deletedAtuacao.isEmpty();
    }

    @Override
    public D getById(ID id) {
        Optional<M> model = repository.findById(id);

        if (!model.isPresent()) {
            throw new WithIdNotFoundException();
        }
        return mapper.toDTO(model.get());
    }

    @Override
    public List<D> getAll() {
        List<M> listModel = repository.findAll();
        return mapper.toListDTO(listModel);
    }

}
