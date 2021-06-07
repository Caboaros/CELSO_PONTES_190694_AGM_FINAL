package com.example.appmobileagenda.model;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;

import java.io.Serializable;

//permite passar informações enquanto chama um intent
public class Personagem implements Serializable {
    private int id = 0;
    private String nome;
    private String altura;
    private String nascimento;
    private String telefone;
    private String endereco;
    private String cep;
    private String rg;
    private String genero;


//    método construtor de Personagem
    public Personagem(String nome, String alt, String nasc, String tel, String end, String cep, String rg, String gen) {
        this.nome = nome;
        this.altura = alt;
        this.nascimento = nasc;
        this.telefone = tel;
        this.endereco = end;
        this.cep = cep;
        this.rg = rg;
        this.genero = gen;
    }
//    construtor vazio
    public Personagem(){}

//  para exibição correta na lista
    @NonNull
    @Override
    public String toString() { return nome; }

//    setters
    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setAltura(String altura) { this.altura = altura; }
    public void setNascimento(String nascimento) { this.nascimento = nascimento; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setCep(String cep) { this.cep = cep; }
    public void setRg(String rg) { this.rg = rg; }
    public void setGenero(String genero) { this.genero = genero; }

//    getters
    public int getId(){ return id; }
    public String getNome() { return nome; }
    public String getAltura() { return altura; }
    public String getNascimento() { return nascimento; }
    public String getTelefone() { return telefone; }
    public String getEndereco() { return endereco; }
    public String getCep() { return cep; }
    public String getRg() { return rg; }
    public String getGenero() { return genero; }

//    verifica se id é válido
    public boolean IdValido() { return id > 0;}

}
