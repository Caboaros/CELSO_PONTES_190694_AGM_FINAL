package com.example.appmobileagenda.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appmobileagenda.R;
import com.example.appmobileagenda.model.Personagem;

public class FormularioPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        EditText edit_nome = findViewById(R.id.edit_nome);
        EditText edit_altura = findViewById(R.id.edit_altura);
        EditText edit_nasc = findViewById(R.id.edit_nasc);
        Button bt_salvar = findViewById(R.id.bt_salvar);

        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pega nome, altura e nascimento inseridos
                String nome = edit_nome.getText().toString();
                String altura = edit_altura.getText().toString();
                String nascimento = edit_nasc.getText().toString();

                //salva os dados inseridos em um personagem temporário
                Personagem personagemSlavo = new Personagem(nome, altura, nascimento);

                //toast que exibe msg de aviso na tela com os dados que foram salvos
                Toast.makeText(FormularioPersonagemActivity.this,
                        personagemSlavo.getNome() + " - " +
                                personagemSlavo.getAltura() + " - " +
                                personagemSlavo.getNascimento(), Toast.LENGTH_SHORT).show();

                //cria um novo Personagem com os dados inseridos
                new Personagem(nome, altura, nascimento);

                //Toast.makeText(FormularioPersonagemActivity.this, "Estou funcionando!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}