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

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalDaninhaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;

@RestController
@RequestMapping("/informacaoAdicionalDaninha")
public class InformacaoAdicionalDaninhaRestController {

    private final AuxiliarUseCase<InformacaoAdicionalDaninhaDTO, Integer> informacaoAdicionalDaninhaUseCase;

    public InformacaoAdicionalDaninhaRestController(AuxiliarUseCase<InformacaoAdicionalDaninhaDTO, Integer> informacaoAdicionalDaninhaUseCase) {
        this.informacaoAdicionalDaninhaUseCase = informacaoAdicionalDaninhaUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<InformacaoAdicionalDaninhaDTO> insert(@RequestBody InformacaoAdicionalDaninhaDTO informacaoAdicionalDaninha) {
        InformacaoAdicionalDaninhaDTO informacaoAdicionalDaninhaDTO = informacaoAdicionalDaninhaUseCase.insert(informacaoAdicionalDaninha);
        return ResponseEntity.status(HttpStatus.CREATED).body(informacaoAdicionalDaninhaDTO);
    }

    @PutMapping
    public ResponseEntity<InformacaoAdicionalDaninhaDTO> update(@RequestBody InformacaoAdicionalDaninhaDTO informacaoAdicionalDaninha) {
        InformacaoAdicionalDaninhaDTO informacaoAdicionalDaninhaDTO = informacaoAdicionalDaninhaUseCase.update(informacaoAdicionalDaninha);
        return ResponseEntity.ok(informacaoAdicionalDaninhaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deleted = informacaoAdicionalDaninhaUseCase.delete(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InformacaoAdicionalDaninhaDTO> getById(@PathVariable int id) {
        InformacaoAdicionalDaninhaDTO informacaoAdicionalDaninha = informacaoAdicionalDaninhaUseCase.getById(id);
        return ResponseEntity.ok(informacaoAdicionalDaninha);
    }

    @GetMapping("/all")
    public ResponseEntity<List<InformacaoAdicionalDaninhaDTO>> getAll() {
        List<InformacaoAdicionalDaninhaDTO> lista = informacaoAdicionalDaninhaUseCase.getAll();
        return ResponseEntity.ok(lista);
    }
}
