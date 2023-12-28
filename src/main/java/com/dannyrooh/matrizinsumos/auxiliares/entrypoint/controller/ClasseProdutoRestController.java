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

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseProdutoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;

@RestController
@RequestMapping("/classeProduto")
public class ClasseProdutoRestController {

    private final AuxiliarUseCase<ClasseProdutoDTO, Integer> classeProdutoUseCase;

    public ClasseProdutoRestController(AuxiliarUseCase<ClasseProdutoDTO, Integer> classeProdutoUseCase) {
        this.classeProdutoUseCase = classeProdutoUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClasseProdutoDTO> insert(@RequestBody ClasseProdutoDTO classeProduto) {
        ClasseProdutoDTO classeProdutoDTO = classeProdutoUseCase.insert(classeProduto);
        return ResponseEntity.status(HttpStatus.CREATED).body(classeProdutoDTO);
    }

    @PutMapping
    public ResponseEntity<ClasseProdutoDTO> update(@RequestBody ClasseProdutoDTO classeProduto) {
        ClasseProdutoDTO classeProdutoDTO = classeProdutoUseCase.update(classeProduto);
        return ResponseEntity.ok(classeProdutoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = classeProdutoUseCase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasseProdutoDTO> getById(@PathVariable int id) {
        ClasseProdutoDTO classeProduto = classeProdutoUseCase.getById(id);
        return ResponseEntity.ok(classeProduto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClasseProdutoDTO>> getAll() {
        List<ClasseProdutoDTO> lista = classeProdutoUseCase.getAll();
        return ResponseEntity.ok(lista);
    }
}
