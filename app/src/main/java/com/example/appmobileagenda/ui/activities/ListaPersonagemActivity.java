package com.example.appmobileagenda.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

    //No início era o caos... então Deus criou a primeira view, e Ele viu que era bom.
    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle(TITULO_APPBAR);
        inicializaListaFab();
        configuraFabNovoPersonagem();
        configuraLista();
    }

    //quando volta, atualiza a lista
    @Override
    protected void onResume() {
        super.onResume();
        atualizaPersonagem();
    }

//    limpa e adiciona personagens novamente na lista
    private void atualizaPersonagem() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

//    cria context menu ao selecionar
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
//        menu.add("Remover");
        getMenuInflater().inflate(R.menu.activity_lista_personagens_menu, menu);
    }

//    pega contexto de item selecionado
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        configuraMenu(item);
        return super.onContextItemSelected(item);
    }

//    chama menu para remover personagem
    private void configuraMenu(@NonNull MenuItem item) {
        int itemId = item.getItemId(); //confirma se é o botão de "Remover" pelo id
        if(itemId == R.id.activity_lista_personagem_menu_remover) {
            new AlertDialog.Builder(this) //chama alert dialog para confirmar remoção
                    .setTitle("Removendo Personagem")
                    .setMessage("Tem certeza que deseja remover?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        //torna positive bt clicável e executa a ação de remover
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo(); //pega info contida na posição da lista
                            Personagem personagemEscolhido = adapter.getItem(menuInfo.position); //pega o item pela posição na lista
                            remove(personagemEscolhido); //remove do dao e do adapter
                        }
                    })
                    .setNegativeButton("Não", null) //faz nada
                    .show(); //exibe alert
        }
    }

    private void remove(Personagem personagem){
        dao.remove(personagem);
        adapter.remove(personagem);
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
        //final List<Personagem> personagens = dao.todos();
        listaDePersonagens(listaDePersonagens);
        configuraItensEdit(listaDePersonagens);
        registerForContextMenu(listaDePersonagens);
    }

    private void listaDePersonagens(ListView listaDePersonagens) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
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