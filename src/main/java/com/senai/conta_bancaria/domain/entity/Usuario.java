package com.senai.conta_bancaria.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Usuario {

    @Id
    @GeneratedValue()
    private long id;

    private String nome;
    private String email;
    private String senha;
}
