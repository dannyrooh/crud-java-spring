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

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoPersistenciaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;

@RestController
@RequestMapping("/tipoPersistencia")
public class TipoPersistenciaRestController {

    private final AuxiliarUseCase<TipoPersistenciaDTO, Integer> tipoPersistenciaUseCase;

    public TipoPersistenciaRestController(AuxiliarUseCase<TipoPersistenciaDTO, Integer> tipoPersistenciaUseCase) {
        this.tipoPersistenciaUseCase = tipoPersistenciaUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TipoPersistenciaDTO> insert(@RequestBody TipoPersistenciaDTO tipoPersistencia) {
        TipoPersistenciaDTO tipoPersistenciaDTO = tipoPersistenciaUseCase.insert(tipoPersistencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoPersistenciaDTO);
    }

    @PutMapping
    public ResponseEntity<TipoPersistenciaDTO> update(@RequestBody TipoPersistenciaDTO tipoPersistencia) {
        TipoPersistenciaDTO tipoPersistenciaDTO = tipoPersistenciaUseCase.update(tipoPersistencia);
        return ResponseEntity.ok(tipoPersistenciaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = tipoPersistenciaUseCase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoPersistenciaDTO> getById(@PathVariable int id) {
        TipoPersistenciaDTO tipoPersistencia = tipoPersistenciaUseCase.getById(id);
        return ResponseEntity.ok(tipoPersistencia);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TipoPersistenciaDTO>> getAll() {
        List<TipoPersistenciaDTO> lista = tipoPersistenciaUseCase.getAll();
        return ResponseEntity.ok(lista);
    }
}
