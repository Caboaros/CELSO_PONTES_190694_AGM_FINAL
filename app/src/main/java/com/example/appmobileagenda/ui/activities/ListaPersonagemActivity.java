package com.example.appmobileagenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobileagenda.R;
import com.example.appmobileagenda.dao.PersonagemDAO;
import com.example.appmobileagenda.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.appmobileagenda.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Personagens";
    private final PersonagemDAO dao = new PersonagemDAO();
    private FloatingActionButton fab_add;
    private ListView listaDePersonagens;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle(TITULO_APPBAR);
        inicializaListaFab();
        configuraFabNovoPersonagem();
    }

    @Override
    protected void onResume() { super.onResume(); configuraLista(); }

    private void configuraFabNovoPersonagem() {
//        configura a ação do botão para abrir o formulário
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { abreFormulario(); }
        });
    }

    private void abreFormulario() {
        startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
    }

    private void configuraLista() {
        final List<Personagem> personagens = dao.todos();
//        relaciona um array adapter com a lista personagem
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));
        configuraItensEdit(listaDePersonagens);
    }

//        torna os itens da lista clicáveis para ser editados
    private void configuraItensEdit(ListView listaDePersonagens) {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//          seleciona o personagem pegando pela posição na lista
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                formularioEditar((Personagem) adapterView.getItemAtPosition(position));
            }
        });
    }

//      cria o intent e abre o formulário
    private void formularioEditar(Personagem personagemEscolhido) {
        Intent abreFormulario = new Intent(
                ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
        abreFormulario.putExtra(CHAVE_PERSONAGEM, personagemEscolhido);
        startActivity(abreFormulario);
    }

//    inicializa lista e fab
    public void inicializaListaFab(){
        fab_add = findViewById(R.id.fab_addPerson);
        listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
    }


}