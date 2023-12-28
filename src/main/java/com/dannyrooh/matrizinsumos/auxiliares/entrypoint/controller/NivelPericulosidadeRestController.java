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

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.NivelPericulosidadeDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;

@RestController
@RequestMapping("/nivelPericulosidade")
public class NivelPericulosidadeRestController {

    private final AuxiliarUseCase<NivelPericulosidadeDTO, Integer> nivelPericulosidadeUseCase;

    public NivelPericulosidadeRestController(AuxiliarUseCase<NivelPericulosidadeDTO, Integer> nivelPericulosidadeUseCase) {
        this.nivelPericulosidadeUseCase = nivelPericulosidadeUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NivelPericulosidadeDTO> insert(@RequestBody NivelPericulosidadeDTO nivelPericulosidade) {
        NivelPericulosidadeDTO nivelPericulosidadeDTO = nivelPericulosidadeUseCase.insert(nivelPericulosidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(nivelPericulosidadeDTO);
    }

    @PutMapping
    public ResponseEntity<NivelPericulosidadeDTO> update(@RequestBody NivelPericulosidadeDTO nivelPericulosidade) {
        NivelPericulosidadeDTO nivelPericulosidadeDTO = nivelPericulosidadeUseCase.update(nivelPericulosidade);
        return ResponseEntity.ok(nivelPericulosidadeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = nivelPericulosidadeUseCase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NivelPericulosidadeDTO> getById(@PathVariable int id) {
        NivelPericulosidadeDTO nivelPericulosidade = nivelPericulosidadeUseCase.getById(id);
        return ResponseEntity.ok(nivelPericulosidade);
    }

    @GetMapping("/all")
    public ResponseEntity<List<NivelPericulosidadeDTO>> getAll() {
        List<NivelPericulosidadeDTO> lista = nivelPericulosidadeUseCase.getAll();
        return ResponseEntity.ok(lista);
    }
}
