package com.dannyrooh.matrizinsumos.grupo.domain.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GrupoDTO {

    private int id;

    // @NotNull(message = "O campo 'nome' é obrigatório e não pode estar em
    // branco.")
    // @Size(min = 2, message = "O nome do grupo deve ter no mímimo 2 caracteres")
    // @Size(max = 50, message = "O nome do grupo deve ter no máximo 50 caracteres")
    private String nome;

    // public GrupoDTO() {
    // }

    // public GrupoDTO(int id, String nome) {
    // this.id = id;
    // this.nome = nome;
    // }

    // public GrupoDTO id(int id) {
    // setId(id);
    // return this;
    // }

    // public GrupoDTO nome(String nome) {
    // setNome(nome);
    // return this;
    // }

    // public int getId() {
    // return this.id;
    // }

    // public void setId(int id) {
    // this.id = id;
    // }

    // public String getNome() {
    // return this.nome;
    // }

    // public void setNome(String nome) {
    // this.nome = nome;
    // }

}
