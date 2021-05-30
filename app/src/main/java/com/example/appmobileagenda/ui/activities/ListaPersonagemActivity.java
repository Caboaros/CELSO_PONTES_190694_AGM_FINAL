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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle("Lista de Personagens");

//        referencia o fab na activity lista de personagem
        FloatingActionButton fab_add = findViewById(R.id.fab_addPerson);
//        configura a ação do botão para abrir o formulário
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });

//        lista de personagens
        /*List<String> personagem = new ArrayList<>(Arrays.asList("Alex", "Ken", "Ryu", "Guile"));

        TextView primeiroPersonagem = findViewById(R.id.textView1);
        TextView segundoPersonagem = findViewById(R.id.textView2);
        TextView terceiroPersonagem = findViewById(R.id.textView3);

        primeiroPersonagem.setText(personagem.get(0));
        segundoPersonagem.setText(personagem.get(1));
        terceiroPersonagem.setText(personagem.get(2));*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        PersonagemDAO personagemDAO = new PersonagemDAO();
//        referencia este listview com o activity_main_lista_personagem
        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);

//        relaciona um array adapter com a lista personagem
        List<Personagem> personagens = personagemDAO.todos();
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));

        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Personagem personagemEscolhido = personagens.get(position);
                Log.i("Personagem", "" + personagemEscolhido);

                Intent abreFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
                startActivity(abreFormulario);
            }
        });
    }
}