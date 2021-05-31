package com.example.appmobileagenda.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

//permite passar informações enquanto chama um intent
public class Personagem implements Serializable {

    private String nome;
    private String altura;
    private String nascimento;
    private int id = 0;

//    método construtor de Personagem
    public Personagem(String nome, String alt, String nasc) {

        this.nome = nome;
        this.altura = alt;
        this.nascimento = nasc;
    }

//    setters
    public void setId(int id){
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

//    getters
    public int getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getAltura() {
        return altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    //  para exibição correta na lista
    @NonNull
    @Override
    public String toString() {
        return nome;
    }



}
