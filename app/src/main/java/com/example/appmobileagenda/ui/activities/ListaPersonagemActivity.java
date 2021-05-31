package com.example.appmobileagenda.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.appmobileagenda.R;
import com.example.appmobileagenda.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.appmobileagenda.dao.PersonagemDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle("Lista de Personagens");

        dao.salva(new Personagem("Ryu", "1,70", "02/04/1979"));
        dao.salva(new Personagem("Ken", "1,80", "02/04/1979"));

//        referencia o fab na activity lista de personagem
        FloatingActionButton fab_add = findViewById(R.id.fab_addPerson);
//        configura a ação do botão para abrir o formulário
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

//        referencia este listview com o activity_main_lista_personagem
        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
        List<Personagem> personagens = dao.todos();

//        relaciona um array adapter com a lista personagem
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));

//        torna os itens da lista clicáveis
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                seleciona o personagem pegando pela posição na lista
                Personagem personagemEscolhido = personagens.get(position);
                Log.i("personagem", "" + personagemEscolhido);

//                cria o intent e abre o formulário
                Intent abreFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
                abreFormulario.putExtra("personagem", personagemEscolhido);
                startActivity(abreFormulario);

            }
        });
    }
}