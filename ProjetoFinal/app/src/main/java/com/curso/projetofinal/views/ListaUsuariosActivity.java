package com.curso.projetofinal.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.curso.projetofinal.R;
import com.curso.projetofinal.adapter.RecyclerViewUserAdapter;
import com.curso.projetofinal.database.MyDbHandler;

public class ListaUsuariosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewUserAdapter adapter;
    private Button bt_voltar_apresen, bt_voltar_main;
    private MyDbHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        //Liga o XML ao Java
        getIds();

        //Inicializa do database
        dbHandler = new MyDbHandler(this, null, null, 1);

        //Configuração da Listagem
        setupRecyclerView();

        //Ação para voltar a tela de inserção de usuário
        irParaMain();

        //Ação para voltar a tela de apresentação
        irParaApresentacao();
    }

    private void getIds(){
        recyclerView = findViewById(R.id.recyclew_view_id);
        bt_voltar_apresen = findViewById(R.id.lista_voltar_apresentacao);
        bt_voltar_main = findViewById(R.id.lista_voltar_main);
    }

    private void setupRecyclerView(){
        adapter = new RecyclerViewUserAdapter(dbHandler.getAllUsers(), dbHandler, getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    private void irParaMain(){
        bt_voltar_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void irParaApresentacao(){
        bt_voltar_apresen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Apresentacao.class);
                startActivity(intent);
            }
        });
    }
}
