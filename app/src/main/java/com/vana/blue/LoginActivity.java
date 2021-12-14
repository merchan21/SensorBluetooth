package com.vana.blue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button Entrar;
    EditText correo, contraseña;
    TextView Cambiar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Cambiar = findViewById(R.id.aRegistro);
        Entrar = findViewById(R.id.Entrar);
        correo = findViewById(R.id.Corre);
        contraseña = findViewById(R.id.Contra);

        mAuth = FirebaseAuth.getInstance();

        Cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(i);
            }
        });
        Entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void userLogin() {

        String mail = correo.getText().toString();
        String password = contraseña.getText().toString();

        if (TextUtils.isEmpty(mail)){
            correo.setError("Ingrese un correo");
            correo.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
            contraseña.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Logueo Correctamente", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
                }else {
                        Log.w("TAG", "Error:", task.getException());
                    }
                    }
            });
        }
    }
}