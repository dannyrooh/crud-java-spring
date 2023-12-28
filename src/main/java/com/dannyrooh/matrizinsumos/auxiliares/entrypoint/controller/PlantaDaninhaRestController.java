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
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;

@RestController
@RequestMapping("/plantaDaninha")
public class PlantaDaninhaRestController {

    private final AuxiliarUseCase<PlantaDaninhaDTO, Integer> plantaDaninhaUseCase;

    public PlantaDaninhaRestController(AuxiliarUseCase<PlantaDaninhaDTO, Integer> plantaDaninhaUseCase) {
        this.plantaDaninhaUseCase = plantaDaninhaUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PlantaDaninhaDTO> insert(@RequestBody PlantaDaninhaDTO plantaDaninha) {
        PlantaDaninhaDTO plantaDaninhaDTO = plantaDaninhaUseCase.insert(plantaDaninha);
        return ResponseEntity.status(HttpStatus.CREATED).body(plantaDaninhaDTO);
    }

    @PutMapping
    public ResponseEntity<PlantaDaninhaDTO> update(@RequestBody PlantaDaninhaDTO plantaDaninha) {
        PlantaDaninhaDTO plantaDaninhaDTO = plantaDaninhaUseCase.update(plantaDaninha);
        return ResponseEntity.ok(plantaDaninhaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = plantaDaninhaUseCase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantaDaninhaDTO> getById(@PathVariable int id) {
        PlantaDaninhaDTO plantaDaninha = plantaDaninhaUseCase.getById(id);
        return ResponseEntity.ok(plantaDaninha);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlantaDaninhaDTO>> getAll() {
        List<PlantaDaninhaDTO> lista = plantaDaninhaUseCase.getAll();
        return ResponseEntity.ok(lista);
    }
}
