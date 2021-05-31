package com.example.appmobileagenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobileagenda.R;
import com.example.appmobileagenda.dao.PersonagemDAO;
import com.example.appmobileagenda.model.Personagem;

public class FormularioPersonagemActivity extends AppCompatActivity {

    private EditText edit_nome;
    private EditText edit_altura;
    private EditText edit_nasc;
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem Personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        setTitle("Salvar novo personagem");

        edit_nome = findViewById(R.id.edit_nome);
        edit_altura = findViewById(R.id.edit_altura);
        edit_nasc = findViewById(R.id.edit_nasc);
        Button bt_salvar = findViewById(R.id.bt_salvar);

//        adiciona o onclicklistener no botão de salvar
        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                pega nome, altura e nascimento inseridos
                String nome = edit_nome.getText().toString();
                String altura = edit_altura.getText().toString();
                String nascimento = edit_nasc.getText().toString();

                if(nome == null || nome.equals("")){
                    Toast.makeText(FormularioPersonagemActivity.this,
                            "É necessário um nome para salvar.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(FormularioPersonagemActivity.this, ListaPersonagemActivity.class));
                }
                else {

//                salva os dados inseridos em um personagem temporário
                    Personagem personagemSalvo = new Personagem(nome, altura, nascimento);

                    dao.salva(personagemSalvo);
                    finish();

                personagemSalvo.setNome(nome);
                personagemSalvo.setAltura(altura);
                personagemSalvo.setNascimento(nascimento);
                dao.editar(personagemSalvo);

                Toast.makeText(FormularioPersonagemActivity.this, "Salvo.", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        pega o intent de quando seleciona um personagem na lista e joga os dados no formulário
        Intent dados = getIntent();
//        verifica se tem extras
        if(dados.hasExtra("personagem")) {

            Personagem personagem = (Personagem) dados.getSerializableExtra("personagem");
            edit_nome.setText(personagem.getNome());
            edit_altura.setText(personagem.getAltura());
            edit_nasc.setText(personagem.getNascimento());

        } //se não tiver, usa um personagem vazio
        else{
            Personagem = new Personagem();
        }
    }


}