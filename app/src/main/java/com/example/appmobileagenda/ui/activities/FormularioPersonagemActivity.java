package com.example.appmobileagenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobileagenda.R;
import com.example.appmobileagenda.dao.PersonagemDAO;
import com.example.appmobileagenda.model.Personagem;

import static com.example.appmobileagenda.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Salvar novo personagem";
    private EditText edit_nome;
    private EditText edit_altura;
    private EditText edit_nasc;
    private Personagem personagem;
    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        setTitle(TITULO_APPBAR);
        inicializaCampos();
        configuraBotao();
        carregaPersonagem();
    }

//  pega o intent de quando seleciona um personagem na lista e joga os dados no formulário
    private void carregaPersonagem() {
        Intent dados = getIntent();
        verificaExtras(dados);
    }

//      verifica se tem extras
    private void verificaExtras(Intent dados) {
        if (dados.hasExtra(CHAVE_PERSONAGEM)) {
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencheCampos(personagem);
        } else { personagem = new Personagem(); }
    }

    private void preencheCampos(Personagem personagem) {
        edit_nome.setText(personagem.getNome());
        edit_altura.setText(personagem.getAltura());
        edit_nasc.setText(personagem.getNascimento());
    }

//    adiciona o onclicklistener no botão de salvar
    private void configuraBotao() {
        Button bt_salvar = findViewById(R.id.bt_salvar);
        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { preencherPersonagem(); editSavePersonagem(); }
        });
    }
//    verifica se id é valido, salva ou edita personagem aberto, e finaliza activity
    private void editSavePersonagem() {
        if (personagem.IdValido()) { dao.editar(personagem); }
        else { dao.salva(personagem); }
        finish();
    }

//    inicializa campos
    private void inicializaCampos() {
        edit_nome = findViewById(R.id.edit_nome);
        edit_altura = findViewById(R.id.edit_altura);
        edit_nasc = findViewById(R.id.edit_nasc);
    }

//    pega nome, altura e nascimento inseridos
    private void preencherPersonagem(){
//        get tudo
        String nome = edit_nome.getText().toString();
        String altura = edit_altura.getText().toString();
        String nascimento = edit_nasc.getText().toString();

//        set tudo
        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
        dao.editar(personagem);
    }
}