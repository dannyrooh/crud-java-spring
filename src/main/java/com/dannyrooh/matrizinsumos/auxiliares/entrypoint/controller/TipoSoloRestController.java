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

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoSoloDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;

@RestController
@RequestMapping("/tipoSolo")
public class TipoSoloRestController {

    private final AuxiliarUseCase<TipoSoloDTO, Integer> tipoSoloUseCase;

    public TipoSoloRestController(AuxiliarUseCase<TipoSoloDTO, Integer> tipoSoloUseCase) {
        this.tipoSoloUseCase = tipoSoloUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TipoSoloDTO> insert(@RequestBody TipoSoloDTO tipoSolo) {
        TipoSoloDTO tipoSoloDTO = tipoSoloUseCase.insert(tipoSolo);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoSoloDTO);
    }

    @PutMapping
    public ResponseEntity<TipoSoloDTO> update(@RequestBody TipoSoloDTO tipoSolo) {
        TipoSoloDTO tipoSoloDTO = tipoSoloUseCase.update(tipoSolo);
        return ResponseEntity.ok(tipoSoloDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = tipoSoloUseCase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoSoloDTO> getById(@PathVariable int id) {
        TipoSoloDTO tipoSolo = tipoSoloUseCase.getById(id);
        return ResponseEntity.ok(tipoSolo);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TipoSoloDTO>> getAll() {
        List<TipoSoloDTO> lista = tipoSoloUseCase.getAll();
        return ResponseEntity.ok(lista);
    }
}
