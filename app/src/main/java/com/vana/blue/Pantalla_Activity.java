package com.vana.blue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Pantalla_Activity extends AppCompatActivity {

    Button login, registro;
    TextView Cambiar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_);

        Cambiar = findViewById(R.id.aRegistro2);
        login = findViewById(R.id.login);
        registro = findViewById(R.id.registro);

        Cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pantalla_Activity.this, RegistroActivity.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pantalla_Activity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pantalla_Activity.this, RegistroActivity.class);
                startActivity(i);
            }
        });
    }
}