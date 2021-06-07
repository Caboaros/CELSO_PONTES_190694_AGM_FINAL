package com.example.appmobileagenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobileagenda.R;
import com.example.appmobileagenda.dao.PersonagemDAO;
import com.example.appmobileagenda.model.Personagem;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import static com.example.appmobileagenda.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Salvar novo personagem";
    private EditText edit_nome;
    private EditText edit_altura;
    private EditText edit_nasc;
    private EditText edit_tel;
    private EditText edit_end;
    private EditText edit_cep;
    private EditText edit_rg;
    private EditText edit_genero;
    private Personagem personagem;
    private final PersonagemDAO dao = new PersonagemDAO();

    //cria o botão de addicionar/salvar personagem no topo
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_personagem_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //gerencia se tem algum item selecionado na lista
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId(); //confirma se é o botão de "Remover" pelo id
        if(itemId == R.id.activity_formulario_personagem_menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    //cria a view, inicializa campos, botão e carrega infos do personagem se for edição
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        setTitle(TITULO_APPBAR);
        inicializaCampos();
        //finalizaFormulario();
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

//    preenche os campos com as info do personagem selecionado
    private void preencheCampos(Personagem personagem) {
        edit_nome.setText(personagem.getNome());
        edit_altura.setText(personagem.getAltura());
        edit_nasc.setText(personagem.getNascimento());
        edit_tel.setText(personagem.getTelefone());
        edit_end.setText(personagem.getEndereco());
        edit_cep.setText(personagem.getCep());
        edit_rg.setText(personagem.getRg());
        edit_genero.setText(personagem.getGenero());
    }

//    adiciona o onclicklistener no botão de salvar
    private void finalizaFormulario() {
        preencherPersonagem();
        salvaPersonagem();
    }
//    verifica se id é valido, salva ou edita personagem aberto, e finaliza activity
    private void salvaPersonagem() {
        if (personagem.IdValido()) dao.editar(personagem);
        else dao.salva(personagem);
        finish();
    }

//    inicializa campos
    private void inicializaCampos() {
        edit_nome = findViewById(R.id.edit_nome);
        edit_altura = findViewById(R.id.edit_altura);
        edit_nasc = findViewById(R.id.edit_nasc);
        edit_tel = findViewById(R.id.edit_tel);
        edit_end = findViewById(R.id.edit_end);
        edit_cep = findViewById(R.id.edit_cep);
        edit_rg = findViewById(R.id.edit_rg);
        edit_genero = findViewById(R.id.edit_genero);

        //formata cada campo adequadamente
        formatarCampo(edit_altura, "N,NN");
        formatarCampo(edit_nasc, "NN/NN/NNNN");
        formatarCampo(edit_tel, "(NN)NNNNN-NNNN");
        formatarCampo(edit_cep, "NNNNN-NNN");
        formatarCampo(edit_rg, "NN.NNN.NNN-N");
    }

//  formata o campo de acordo com a string desejada e cria um listener para verificar o input
    private void formatarCampo(EditText campo, String formato) {
        SimpleMaskFormatter smfCampo = new SimpleMaskFormatter(formato);
        MaskTextWatcher mtwCampo = new MaskTextWatcher(campo, smfCampo);
        campo.addTextChangedListener(mtwCampo);
    }

    //    pega nome, altura e nascimento inseridos
    private void preencherPersonagem(){
//        get campos
        String nome = edit_nome.getText().toString();
        String altura = edit_altura.getText().toString();
        String nascimento = edit_nasc.getText().toString();
        String telefone = edit_tel.getText().toString();
        String endereco = edit_end.getText().toString();
        String cep = edit_cep.getText().toString();
        String rg = edit_rg.getText().toString();
        String genero = edit_genero.getText().toString();


//        set campos
        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
        personagem.setTelefone(telefone);
        personagem.setEndereco(endereco);
        personagem.setCep(cep);
        personagem.setRg(rg);
        personagem.setGenero(genero);
        dao.editar(personagem);
    }
}