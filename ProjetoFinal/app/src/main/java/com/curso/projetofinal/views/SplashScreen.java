package com.curso.projetofinal.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.curso.projetofinal.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Aqui é disparado um Timer enquanto carrega o aplicativo
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                irParaApresentacao();
            }
        },2000);
    }

    //Método para ir para a Activity de Apresentação após o Timer terminar
    private void irParaApresentacao() {
        Intent intent = new Intent(this, Apresentacao.class);
        startActivity(intent);
    }
}
