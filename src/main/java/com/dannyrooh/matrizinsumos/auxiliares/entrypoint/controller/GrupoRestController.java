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

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.GrupoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;

@RestController
@RequestMapping("/grupo")
public class GrupoRestController {

    private final AuxiliarUseCase<GrupoDTO, Integer> grupoUseCase;

    public GrupoRestController(AuxiliarUseCase<GrupoDTO, Integer> grupoUseCase) {
        this.grupoUseCase = grupoUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GrupoDTO> insert(@RequestBody GrupoDTO grupo) {
        GrupoDTO grupoDTO = grupoUseCase.insert(grupo);
        return ResponseEntity.status(HttpStatus.CREATED).body(grupoDTO);
    }

    @PutMapping
    public ResponseEntity<GrupoDTO> update(@RequestBody GrupoDTO grupo) {
        GrupoDTO grupoDTO = grupoUseCase.update(grupo);
        return ResponseEntity.ok(grupoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = grupoUseCase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoDTO> getById(@PathVariable int id) {
        GrupoDTO grupo = grupoUseCase.getById(id);
        return ResponseEntity.ok(grupo);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GrupoDTO>> getAll() {
        List<GrupoDTO> lista = grupoUseCase.getAll();
        return ResponseEntity.ok(lista);
    }
}
