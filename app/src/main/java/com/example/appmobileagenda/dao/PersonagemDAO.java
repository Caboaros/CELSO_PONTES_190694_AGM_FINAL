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
        contadorId++;
    }

    public void editar (Personagem personagem){
        Personagem personagemEscolhido = null;

        for (Personagem p: personagens){
           if(p.getId() == personagem.getId()){
               personagemEscolhido = p;
           }
        }
        if (personagemEscolhido != null){
            int posicaoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoPersonagem, personagem);
        }
    }

//    retorna os personagens salvos no dao
    public List<Personagem> todos(){ return new ArrayList<>(personagens); }
}
