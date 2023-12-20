package com.dannyrooh.matrizinsumos.grupo.app.dto;

public class GrupoDTO {

    private int id;

    public GrupoDTO(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public GrupoDTO id(int id) {
        setId(id);
        return this;
    }

    public GrupoDTO nome(String nome) {
        setNome(nome);
        return this;
    }

    private String nome;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
