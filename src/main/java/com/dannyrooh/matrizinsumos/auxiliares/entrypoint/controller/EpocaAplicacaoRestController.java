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

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.EpocaAplicacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;

@RestController
@RequestMapping("/epocaAplicacao")
public class EpocaAplicacaoRestController {

    private final AuxiliarUseCase<EpocaAplicacaoDTO, Integer> epocaAplicacaoUseCase;

    public EpocaAplicacaoRestController(AuxiliarUseCase<EpocaAplicacaoDTO, Integer> epocaAplicacaoUseCase) {
        this.epocaAplicacaoUseCase = epocaAplicacaoUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EpocaAplicacaoDTO> insert(@RequestBody EpocaAplicacaoDTO epocaAplicacao) {
        EpocaAplicacaoDTO epocaAplicacaoDTO = epocaAplicacaoUseCase.insert(epocaAplicacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(epocaAplicacaoDTO);
    }

    @PutMapping
    public ResponseEntity<EpocaAplicacaoDTO> update(@RequestBody EpocaAplicacaoDTO epocaAplicacao) {
        EpocaAplicacaoDTO epocaAplicacaoDTO = epocaAplicacaoUseCase.update(epocaAplicacao);
        return ResponseEntity.ok(epocaAplicacaoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = epocaAplicacaoUseCase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpocaAplicacaoDTO> getById(@PathVariable int id) {
        EpocaAplicacaoDTO epocaAplicacao = epocaAplicacaoUseCase.getById(id);
        return ResponseEntity.ok(epocaAplicacao);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EpocaAplicacaoDTO>> getAll() {
        List<EpocaAplicacaoDTO> lista = epocaAplicacaoUseCase.getAll();
        return ResponseEntity.ok(lista);
    }
}
