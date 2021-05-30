package com.example.appmobileagenda.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.appmobileagenda.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);

        //lista de personagens
        List<String> personagem = new ArrayList<>(Arrays.asList("Alex", "Ken", "Ryu", "Guile"));

        //referencia este listview com o activity_main_lista_personagem
        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);

        //relaciona um array adapter com a lista personagem
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagem));

//        TextView primeiroPersonagem = findViewById(R.id.textView1);
//        TextView segundoPersonagem = findViewById(R.id.textView2);
//        TextView terceiroPersonagem = findViewById(R.id.textView3);
//
//        primeiroPersonagem.setText(personagem.get(0));
//        segundoPersonagem.setText(personagem.get(1));
//        terceiroPersonagem.setText(personagem.get(2));
    }
}