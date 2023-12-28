package com.dannyrooh.matrizinsumos.auxiliares.generic.dto;

public abstract class AuxiliarDTO {

    private Integer id;

    private String nome;

    public abstract int getNameSize();

    public AuxiliarDTO() {
    }

    public AuxiliarDTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public AuxiliarDTO id(Integer id) {
        setId(id);
        return this;
    }

    public AuxiliarDTO nome(String nome) {
        setNome(nome);
        return this;
    }

}
