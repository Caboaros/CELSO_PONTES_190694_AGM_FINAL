package com.example.appmobileagenda.dao;

import com.example.appmobileagenda.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();

    public void salva(Personagem personagemSalvo) {
//        adiciona o novo personagem no dao
        personagens.add(personagemSalvo);
    }

    public List<Personagem> todos(){
//        retorna os personagens salvos no dao
        return new ArrayList<>(personagens);
    }
}
