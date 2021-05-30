package com.example.appmobileagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> personagem = new ArrayList<>(Arrays.asList("Alex", "Ken", "Ryu", "Guile"));

        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);

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