package com.dannyrooh.matrizinsumos.grupo.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dannyrooh.matrizinsumos.grupo.app.dto.GrupoDTO;
import com.dannyrooh.matrizinsumos.grupo.app.usecase.GrupoUseCase;

@RestController
@RequestMapping("/grupo")
public class GrupoRestController {

    private final GrupoUseCase grupousecase;

    public GrupoRestController(GrupoUseCase grupousecase) {
        this.grupousecase = grupousecase;
    }

    @PostMapping
    public ResponseEntity<GrupoDTO> insert(@Valid @RequestBody GrupoDTO grupo) {
        GrupoDTO grupoDTO = this.grupousecase.insert(grupo);
        return ResponseEntity.ok(grupoDTO);
    }

    @PutMapping
    public ResponseEntity<GrupoDTO> update(@RequestBody GrupoDTO grupo) {
        GrupoDTO grupoDTO = this.grupousecase.update(grupo);
        return ResponseEntity.ok(grupoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = this.grupousecase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoDTO> getById(@PathVariable int id) {
        GrupoDTO grupo = this.grupousecase.getById(id);
        return ResponseEntity.ok(grupo);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GrupoDTO>> getAll() {
        List<GrupoDTO> lista = this.grupousecase.getAll();
        return ResponseEntity.ok(lista);
    }
}
