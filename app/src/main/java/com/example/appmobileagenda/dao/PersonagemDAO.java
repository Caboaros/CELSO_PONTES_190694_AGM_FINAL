package com.example.appmobileagenda.dao;

import com.example.appmobileagenda.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();
    private static int contadorId = 1;

    public void salva(Personagem personagemSalvo) {
//        adiciona o novo personagem no dao
        personagemSalvo.setId(contadorId);
        personagens.add(personagemSalvo);
        autalizaId();
    }
//        aumenta o contador de Id
    private void autalizaId() {
        contadorId++;
    }

    public void editar (Personagem personagem){
        Personagem personagemEscolhido = buscaPersonagemId(personagem);
//        define a posição antiga do personagem da lista
        if (personagemEscolhido != null){
            int posicaoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoPersonagem, personagem);
        }
    }
//        verifica um por um comparando por Id
    private Personagem buscaPersonagemId(Personagem personagem) {
        for (Personagem p: personagens){
           if(p.getId() == personagem.getId()) { return p; }
        } return null;
    }

//    retorna os personagens salvos no dao
    public List<Personagem> todos(){ return new ArrayList<>(personagens); }
}
