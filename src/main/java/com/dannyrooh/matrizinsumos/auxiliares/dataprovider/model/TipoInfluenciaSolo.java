package com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TipoInfluenciaSolo")
@Getter
@Setter
public class TipoInfluenciaSolo {

    @Id
    @Column(nullable = false, updatable = false, name = "Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false, length = 50, name = "Nome")
    private String nome;
}
