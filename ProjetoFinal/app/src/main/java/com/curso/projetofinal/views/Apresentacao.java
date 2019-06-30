package com.curso.projetofinal.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.curso.projetofinal.R;

public class Apresentacao extends AppCompatActivity {

    private Button bt_cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

        bt_cadastro = findViewById(R.id.bt_ir_cadastro);

        irParaCadastro();
    }

    //Método para ir para a tela de Cadastro (MainActivity) após clicar no botão
    private void irParaCadastro(){
        bt_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
