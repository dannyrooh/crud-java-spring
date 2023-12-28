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

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoManifestacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;

@RestController
@RequestMapping("/tipoManifestacao")
public class TipoManifestacaoRestController {

    private final AuxiliarUseCase<TipoManifestacaoDTO, Integer> tipoManifestacaoUseCase;

    public TipoManifestacaoRestController(AuxiliarUseCase<TipoManifestacaoDTO, Integer> tipoManifestacaoUseCase) {
        this.tipoManifestacaoUseCase = tipoManifestacaoUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TipoManifestacaoDTO> insert(@RequestBody TipoManifestacaoDTO tipoManifestacao) {
        TipoManifestacaoDTO tipoManifestacaoDTO = tipoManifestacaoUseCase.insert(tipoManifestacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoManifestacaoDTO);
    }

    @PutMapping
    public ResponseEntity<TipoManifestacaoDTO> update(@RequestBody TipoManifestacaoDTO tipoManifestacao) {
        TipoManifestacaoDTO tipoManifestacaoDTO = tipoManifestacaoUseCase.update(tipoManifestacao);
        return ResponseEntity.ok(tipoManifestacaoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = tipoManifestacaoUseCase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoManifestacaoDTO> getById(@PathVariable int id) {
        TipoManifestacaoDTO tipoManifestacao = tipoManifestacaoUseCase.getById(id);
        return ResponseEntity.ok(tipoManifestacao);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TipoManifestacaoDTO>> getAll() {
        List<TipoManifestacaoDTO> lista = tipoManifestacaoUseCase.getAll();
        return ResponseEntity.ok(lista);
    }
}
