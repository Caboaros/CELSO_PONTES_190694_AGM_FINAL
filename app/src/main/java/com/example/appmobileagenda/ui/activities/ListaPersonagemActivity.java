package com.example.appmobileagenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
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
    private ArrayAdapter<Personagem> adapter; //carinha maroto pra guardar info do adapter

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle(TITULO_APPBAR);
        inicializaListaFab();
        configuraFabNovoPersonagem();
        configuraLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(dao.todos());
    }
//    cria context menu ao selecionar
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Remover");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Personagem personagemEscolhido = adapter.getItem(menuInfo.position);
        adapter.remove(personagemEscolhido);
        return super.onContextItemSelected(item);
    }

    //    configura a ação do botão para abrir o formulário
    private void configuraFabNovoPersonagem() {
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { abreFormulario(); }
        });
    }

    private void abreFormulario() {
        startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
    }

//    relaciona um array adapter com a lista personagem
    private void configuraLista() {
        final List<Personagem> personagens = dao.todos();
        listaDePersonagens(listaDePersonagens, personagens);
        configuraItensEdit(listaDePersonagens);
    }

    private void listaDePersonagens(ListView listaDePersonagens, List<Personagem> personagens) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens);
        listaDePersonagens.setAdapter(adapter);
    }

    //        torna os itens da lista clicáveis para ser editados e
//        seleciona o personagem pegando pela posição na lista
    private void configuraItensEdit(ListView listaDePersonagens) {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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