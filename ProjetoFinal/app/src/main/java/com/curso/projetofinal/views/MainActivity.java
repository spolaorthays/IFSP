package com.curso.projetofinal.views;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.curso.projetofinal.database.MyDbHandler;
import com.curso.projetofinal.R;
import com.curso.projetofinal.model.Usuario;

/******************************************************

 >> Instituto Federal de São Paulo - Campus Sertãozinho

 >> Disciplina......: M4DADM

 >> Programação de Computadores e Dispositivos Móveis

 >> Aluna...........: Thays Spolaor

 ******************************************************/

public class MainActivity extends AppCompatActivity {

    private TextInputEditText nome, cpf, idade, telefone, email;
    private Button inserirDados, listarRegistros, btVoltar;
    private MyDbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Liga o XML ao arquivo Java
        getIds();

        //Inicializando o banco de dados
        dbHandler = new MyDbHandler(this, null, null, 1);

        //Volta para a activity de Apresentação do App
        voltarParaApresentacao();

        //Insere o usuário no banco de dados ao click do botão
        setInserirDados();

        //Abre a tela de Lista de Usuários cadastrados
        setListarRegistros();
    }

    private void getIds(){
        nome = findViewById(R.id.edt_nome);
        cpf = findViewById(R.id.edt_cpf);
        idade = findViewById(R.id.edt_idade);
        telefone = findViewById(R.id.edt_telefone);
        email = findViewById(R.id.edt_email);

        inserirDados = findViewById(R.id.bt_inserir);
        listarRegistros = findViewById(R.id.bt_listar);
        btVoltar = findViewById(R.id.bt_voltar_main);
    }

    //Método para adicionar o usuário no banco de dados
    private void adicionarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNome(nome.getEditableText().toString());
        usuario.setCpf(cpf.getEditableText().toString());
        usuario.setIdade(idade.getEditableText().toString());
        usuario.setTelefone(telefone.getEditableText().toString());
        usuario.setEmail(email.getEditableText().toString());

        usuario.setId((int)dbHandler.insertUser(usuario));
    }

    private void setInserirDados(){
        inserirDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nome.getEditableText().toString().equals("") || cpf.getEditableText().toString().equals("") ||
                        idade.getEditableText().toString().equals("") || telefone.getEditableText().toString().equals("") ||
                        email.getEditableText().toString().equals("")){
                    Snackbar.make(v, "Por favor, preencha todos os dados", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Ok, entendi!", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    closeOptionsMenu();
                                }
                            }).show();
                }else {
                    adicionarUsuario();
                    Toast.makeText(getApplicationContext(), "Dados inseridos com sucesso", Toast.LENGTH_LONG).show();

                    nome.setText("");
                    cpf.setText("");
                    idade.setText("");
                    telefone.setText("");
                    email.setText("");
                }


            }
        });

    }

    private void setListarRegistros(){
        listarRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListaUsuariosActivity.class);
                startActivity(intent);
            }
        });
    }

    private void voltarParaApresentacao(){
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Apresentacao.class);
                startActivity(intent);
            }
        });
    }
}
