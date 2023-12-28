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

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ModoAcaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;

@RestController
@RequestMapping("/modoAcao")
public class ModoAcaoRestController {

    private final AuxiliarUseCase<ModoAcaoDTO, Integer> modoAcaoUseCase;

    public ModoAcaoRestController(AuxiliarUseCase<ModoAcaoDTO, Integer> modoAcaoUseCase) {
        this.modoAcaoUseCase = modoAcaoUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ModoAcaoDTO> insert(@RequestBody ModoAcaoDTO modoAcao) {
        ModoAcaoDTO modoAcaoDTO = modoAcaoUseCase.insert(modoAcao);
        return ResponseEntity.status(HttpStatus.CREATED).body(modoAcaoDTO);
    }

    @PutMapping
    public ResponseEntity<ModoAcaoDTO> update(@RequestBody ModoAcaoDTO modoAcao) {
        ModoAcaoDTO modoAcaoDTO = modoAcaoUseCase.update(modoAcao);
        return ResponseEntity.ok(modoAcaoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = modoAcaoUseCase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModoAcaoDTO> getById(@PathVariable int id) {
        ModoAcaoDTO modoAcao = modoAcaoUseCase.getById(id);
        return ResponseEntity.ok(modoAcao);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ModoAcaoDTO>> getAll() {
        List<ModoAcaoDTO> lista = modoAcaoUseCase.getAll();
        return ResponseEntity.ok(lista);
    }
}
