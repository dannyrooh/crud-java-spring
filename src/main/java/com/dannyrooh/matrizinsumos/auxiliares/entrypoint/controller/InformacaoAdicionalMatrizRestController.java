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

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalMatrizDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;

@RestController
@RequestMapping("/informacaoAdicionalMatriz")
public class InformacaoAdicionalMatrizRestController {

    private final AuxiliarUseCase<InformacaoAdicionalMatrizDTO, Integer> informacaoAdicionalMatrizUseCase;

    public InformacaoAdicionalMatrizRestController(AuxiliarUseCase<InformacaoAdicionalMatrizDTO, Integer> informacaoAdicionalMatrizUseCase) {
        this.informacaoAdicionalMatrizUseCase = informacaoAdicionalMatrizUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<InformacaoAdicionalMatrizDTO> insert(@RequestBody InformacaoAdicionalMatrizDTO informacaoAdicionalMatriz) {
        InformacaoAdicionalMatrizDTO informacaoAdicionalMatrizDTO = informacaoAdicionalMatrizUseCase.insert(informacaoAdicionalMatriz);
        return ResponseEntity.status(HttpStatus.CREATED).body(informacaoAdicionalMatrizDTO);
    }

    @PutMapping
    public ResponseEntity<InformacaoAdicionalMatrizDTO> update(@RequestBody InformacaoAdicionalMatrizDTO informacaoAdicionalMatriz) {
        InformacaoAdicionalMatrizDTO informacaoAdicionalMatrizDTO = informacaoAdicionalMatrizUseCase.update(informacaoAdicionalMatriz);
        return ResponseEntity.ok(informacaoAdicionalMatrizDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = informacaoAdicionalMatrizUseCase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InformacaoAdicionalMatrizDTO> getById(@PathVariable int id) {
        InformacaoAdicionalMatrizDTO informacaoAdicionalMatriz = informacaoAdicionalMatrizUseCase.getById(id);
        return ResponseEntity.ok(informacaoAdicionalMatriz);
    }

    @GetMapping("/all")
    public ResponseEntity<List<InformacaoAdicionalMatrizDTO>> getAll() {
        List<InformacaoAdicionalMatrizDTO> lista = informacaoAdicionalMatrizUseCase.getAll();
        return ResponseEntity.ok(lista);
    }
}
