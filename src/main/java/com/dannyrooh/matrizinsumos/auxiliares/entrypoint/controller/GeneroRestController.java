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

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.GeneroDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;

@RestController
@RequestMapping("/genero")
public class GeneroRestController {

    private final AuxiliarUseCase<GeneroDTO, Integer> generoUseCase;

    public GeneroRestController(AuxiliarUseCase<GeneroDTO, Integer> generoUseCase) {
        this.generoUseCase = generoUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GeneroDTO> insert(@RequestBody GeneroDTO genero) {
        GeneroDTO generoDTO = generoUseCase.insert(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoDTO);
    }

    @PutMapping
    public ResponseEntity<GeneroDTO> update(@RequestBody GeneroDTO genero) {
        GeneroDTO generoDTO = generoUseCase.update(genero);
        return ResponseEntity.ok(generoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = generoUseCase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> getById(@PathVariable int id) {
        GeneroDTO genero = generoUseCase.getById(id);
        return ResponseEntity.ok(genero);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GeneroDTO>> getAll() {
        List<GeneroDTO> lista = generoUseCase.getAll();
        return ResponseEntity.ok(lista);
    }
}
