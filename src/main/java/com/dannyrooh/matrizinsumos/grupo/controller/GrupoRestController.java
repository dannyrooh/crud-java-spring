package com.dannyrooh.matrizinsumos.grupo.controller;

import java.util.List;
import java.util.Arrays;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dannyrooh.matrizinsumos.grupo.dto.GrupoDTO;

@RestController
@RequestMapping("/grupo")
public class GrupoRestController {

    @PostMapping
    public GrupoDTO insert(@RequestBody GrupoDTO grupo) {
        return new GrupoDTO(10, grupo.getNome());
    }

    @PutMapping
    public GrupoDTO update(@RequestBody GrupoDTO grupo) {
        grupo.setId(grupo.getId() + 1);
        return grupo;
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable int id) {
        return id;
    }

    @GetMapping("/{id}")
    public GrupoDTO getById(@PathVariable int id) {
        GrupoDTO grupo = new GrupoDTO(id, "John Doe");
        return grupo;
    }

    @GetMapping("/all")
    public List<GrupoDTO> getAllGrupos() {
        List<GrupoDTO> lista = Arrays.asList(
                new GrupoDTO(1, "John Doe"),
                new GrupoDTO(2, "Mary Crazy"),
                new GrupoDTO(3, "Patrick Hungry")
        );
        return lista;
    }
}
