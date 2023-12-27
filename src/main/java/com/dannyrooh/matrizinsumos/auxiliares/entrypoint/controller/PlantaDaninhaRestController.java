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

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.PlantaDaninhaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.PlantaDaninhaUseCase;

@RestController
@RequestMapping("/grupo")
public class PlantaDaninhaRestController {

    private final PlantaDaninhaUseCase grupousecase;

    public PlantaDaninhaRestController(PlantaDaninhaUseCase grupousecase) {
        this.grupousecase = grupousecase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PlantaDaninhaDTO> insert(@RequestBody PlantaDaninhaDTO grupo) {
        PlantaDaninhaDTO grupoDTO = this.grupousecase.insert(grupo);
        return ResponseEntity.status(HttpStatus.CREATED).body(grupoDTO);
    }

    @PutMapping
    public ResponseEntity<PlantaDaninhaDTO> update(@RequestBody PlantaDaninhaDTO grupo) {
        PlantaDaninhaDTO grupoDTO = this.grupousecase.update(grupo);
        return ResponseEntity.ok(grupoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = this.grupousecase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantaDaninhaDTO> getById(@PathVariable int id) {
        PlantaDaninhaDTO grupo = this.grupousecase.getById(id);
        return ResponseEntity.ok(grupo);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlantaDaninhaDTO>> getAll() {
        List<PlantaDaninhaDTO> lista = this.grupousecase.getAll();
        return ResponseEntity.ok(lista);
    }
}
