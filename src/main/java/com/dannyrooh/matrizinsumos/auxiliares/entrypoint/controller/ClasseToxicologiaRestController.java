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

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseToxicologiaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;

@RestController
@RequestMapping("/classeToxicologia")
public class ClasseToxicologiaRestController {

    private final AuxiliarUseCase<ClasseToxicologiaDTO, Integer> classeToxicologiaUseCase;

    public ClasseToxicologiaRestController(AuxiliarUseCase<ClasseToxicologiaDTO, Integer> classeToxicologiaUseCase) {
        this.classeToxicologiaUseCase = classeToxicologiaUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClasseToxicologiaDTO> insert(@RequestBody ClasseToxicologiaDTO classeToxicologia) {
        ClasseToxicologiaDTO classeToxicologiaDTO = classeToxicologiaUseCase.insert(classeToxicologia);
        return ResponseEntity.status(HttpStatus.CREATED).body(classeToxicologiaDTO);
    }

    @PutMapping
    public ResponseEntity<ClasseToxicologiaDTO> update(@RequestBody ClasseToxicologiaDTO classeToxicologia) {
        ClasseToxicologiaDTO classeToxicologiaDTO = classeToxicologiaUseCase.update(classeToxicologia);
        return ResponseEntity.ok(classeToxicologiaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = classeToxicologiaUseCase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasseToxicologiaDTO> getById(@PathVariable int id) {
        ClasseToxicologiaDTO classeToxicologia = classeToxicologiaUseCase.getById(id);
        return ResponseEntity.ok(classeToxicologia);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClasseToxicologiaDTO>> getAll() {
        List<ClasseToxicologiaDTO> lista = classeToxicologiaUseCase.getAll();
        return ResponseEntity.ok(lista);
    }
}
