package com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoLixiviacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;

@RestController
@RequestMapping("/tipoLixiviacao")
public class TipoLixiviacaoRestController {

    private final AuxiliarUseCase<TipoLixiviacaoDTO, Integer> tipoLixiviacaoUseCase;

    public TipoLixiviacaoRestController(AuxiliarUseCase<TipoLixiviacaoDTO, Integer> tipoLixiviacaoUseCase) {
        this.tipoLixiviacaoUseCase = tipoLixiviacaoUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TipoLixiviacaoDTO> insert(@RequestBody TipoLixiviacaoDTO tipoLixiviacao) {
        TipoLixiviacaoDTO tipoLixiviacaoDTO = tipoLixiviacaoUseCase.insert(tipoLixiviacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoLixiviacaoDTO);
    }

    @PutMapping
    public ResponseEntity<TipoLixiviacaoDTO> update(@RequestBody TipoLixiviacaoDTO tipoLixiviacao) {
        TipoLixiviacaoDTO tipoLixiviacaoDTO = tipoLixiviacaoUseCase.update(tipoLixiviacao);
        return ResponseEntity.ok(tipoLixiviacaoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = tipoLixiviacaoUseCase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoLixiviacaoDTO> getById(@PathVariable int id) {
        TipoLixiviacaoDTO tipoLixiviacao = tipoLixiviacaoUseCase.getById(id);
        return ResponseEntity.ok(tipoLixiviacao);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TipoLixiviacaoDTO>> getAll() {
        List<TipoLixiviacaoDTO> lista = tipoLixiviacaoUseCase.getAll();
        return ResponseEntity.ok(lista);
    }
}
