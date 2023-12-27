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
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.InformacaoAdicionalMatrizUseCase;

@RestController
@RequestMapping("/grupo")
public class InformacaoAdicionalMatrizRestController {

    private final InformacaoAdicionalMatrizUseCase grupousecase;

    public InformacaoAdicionalMatrizRestController(InformacaoAdicionalMatrizUseCase grupousecase) {
        this.grupousecase = grupousecase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<InformacaoAdicionalMatrizDTO> insert(@RequestBody InformacaoAdicionalMatrizDTO grupo) {
        InformacaoAdicionalMatrizDTO grupoDTO = this.grupousecase.insert(grupo);
        return ResponseEntity.status(HttpStatus.CREATED).body(grupoDTO);
    }

    @PutMapping
    public ResponseEntity<InformacaoAdicionalMatrizDTO> update(@RequestBody InformacaoAdicionalMatrizDTO grupo) {
        InformacaoAdicionalMatrizDTO grupoDTO = this.grupousecase.update(grupo);
        return ResponseEntity.ok(grupoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = this.grupousecase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InformacaoAdicionalMatrizDTO> getById(@PathVariable int id) {
        InformacaoAdicionalMatrizDTO grupo = this.grupousecase.getById(id);
        return ResponseEntity.ok(grupo);
    }

    @GetMapping("/all")
    public ResponseEntity<List<InformacaoAdicionalMatrizDTO>> getAll() {
        List<InformacaoAdicionalMatrizDTO> lista = this.grupousecase.getAll();
        return ResponseEntity.ok(lista);
    }
}
