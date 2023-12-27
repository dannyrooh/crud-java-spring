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

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.AtuacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.AtuacaoUseCase;

@RestController
@RequestMapping("/atuacao")
public class AtuacaoRestController {

    private final AtuacaoUseCase atuacaousecase;

    public AtuacaoRestController(AtuacaoUseCase atuacaousecase) {
        this.atuacaousecase = atuacaousecase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AtuacaoDTO> insert(@RequestBody AtuacaoDTO atuacao) {
        AtuacaoDTO atuacaoDTO = this.atuacaousecase.insert(atuacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(atuacaoDTO);
    }

    @PutMapping
    public ResponseEntity<AtuacaoDTO> update(@RequestBody AtuacaoDTO atuacao) {
        AtuacaoDTO atuacaoDTO = this.atuacaousecase.update(atuacao);
        return ResponseEntity.ok(atuacaoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = this.atuacaousecase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtuacaoDTO> getById(@PathVariable int id) {
        AtuacaoDTO atuacao = this.atuacaousecase.getById(id);
        return ResponseEntity.ok(atuacao);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AtuacaoDTO>> getAll() {
        List<AtuacaoDTO> lista = this.atuacaousecase.getAll();
        return ResponseEntity.ok(lista);
    }
}
