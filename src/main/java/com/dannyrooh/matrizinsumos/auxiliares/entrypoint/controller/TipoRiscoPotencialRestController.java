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

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoRiscoPotencialDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;

@RestController
@RequestMapping("/tipoRiscoPotencial")
public class TipoRiscoPotencialRestController {

    private final AuxiliarUseCase<TipoRiscoPotencialDTO, Integer> tipoRiscoPotencialUseCase;

    public TipoRiscoPotencialRestController(AuxiliarUseCase<TipoRiscoPotencialDTO, Integer> tipoRiscoPotencialUseCase) {
        this.tipoRiscoPotencialUseCase = tipoRiscoPotencialUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TipoRiscoPotencialDTO> insert(@RequestBody TipoRiscoPotencialDTO tipoRiscoPotencial) {
        TipoRiscoPotencialDTO tipoRiscoPotencialDTO = tipoRiscoPotencialUseCase.insert(tipoRiscoPotencial);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoRiscoPotencialDTO);
    }

    @PutMapping
    public ResponseEntity<TipoRiscoPotencialDTO> update(@RequestBody TipoRiscoPotencialDTO tipoRiscoPotencial) {
        TipoRiscoPotencialDTO tipoRiscoPotencialDTO = tipoRiscoPotencialUseCase.update(tipoRiscoPotencial);
        return ResponseEntity.ok(tipoRiscoPotencialDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = tipoRiscoPotencialUseCase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoRiscoPotencialDTO> getById(@PathVariable int id) {
        TipoRiscoPotencialDTO tipoRiscoPotencial = tipoRiscoPotencialUseCase.getById(id);
        return ResponseEntity.ok(tipoRiscoPotencial);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TipoRiscoPotencialDTO>> getAll() {
        List<TipoRiscoPotencialDTO> lista = tipoRiscoPotencialUseCase.getAll();
        return ResponseEntity.ok(lista);
    }
}
