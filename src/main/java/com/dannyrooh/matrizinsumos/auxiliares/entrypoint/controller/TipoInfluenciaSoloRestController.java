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

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoInfluenciaSoloDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;

@RestController
@RequestMapping("/tipoInfluenciaSolo")
public class TipoInfluenciaSoloRestController {

    private final AuxiliarUseCase<TipoInfluenciaSoloDTO, Integer> tipoInfluenciaSoloUseCase;

    public TipoInfluenciaSoloRestController(AuxiliarUseCase<TipoInfluenciaSoloDTO, Integer> tipoInfluenciaSoloUseCase) {
        this.tipoInfluenciaSoloUseCase = tipoInfluenciaSoloUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TipoInfluenciaSoloDTO> insert(@RequestBody TipoInfluenciaSoloDTO tipoInfluenciaSolo) {
        TipoInfluenciaSoloDTO tipoInfluenciaSoloDTO = tipoInfluenciaSoloUseCase.insert(tipoInfluenciaSolo);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoInfluenciaSoloDTO);
    }

    @PutMapping
    public ResponseEntity<TipoInfluenciaSoloDTO> update(@RequestBody TipoInfluenciaSoloDTO tipoInfluenciaSolo) {
        TipoInfluenciaSoloDTO tipoInfluenciaSoloDTO = tipoInfluenciaSoloUseCase.update(tipoInfluenciaSolo);
        return ResponseEntity.ok(tipoInfluenciaSoloDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = tipoInfluenciaSoloUseCase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoInfluenciaSoloDTO> getById(@PathVariable int id) {
        TipoInfluenciaSoloDTO tipoInfluenciaSolo = tipoInfluenciaSoloUseCase.getById(id);
        return ResponseEntity.ok(tipoInfluenciaSolo);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TipoInfluenciaSoloDTO>> getAll() {
        List<TipoInfluenciaSoloDTO> lista = tipoInfluenciaSoloUseCase.getAll();
        return ResponseEntity.ok(lista);
    }
}
