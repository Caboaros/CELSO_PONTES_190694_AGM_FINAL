package com.example.appmobileagenda.model;

import androidx.annotation.NonNull;

public class Personagem {

    private final String nome;
    private final String altura;
    private final String nascimento;

    //método construtor de Personagem
    public Personagem(String nome, String alt, String nasc) {

        this.nome = nome;
        this.altura = alt;
        this.nascimento = nasc;
    }

//  para exibição correta na lista
    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    //getters
    public String getNome() {
        return nome;
    }

    public String getAltura() {
        return altura;
    }

    public String getNascimento() {
        return nascimento;
    }
}
