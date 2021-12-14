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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    Button registro;
    EditText nombre, correo, contrasena;
    TextView Cambiar;

    private String userID;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Cambiar = findViewById(R.id.alogin);
        registro = findViewById(R.id.Guardar);
        nombre = findViewById(R.id.Nombre);
        correo = findViewById(R.id.Correo);
        contrasena = findViewById(R.id.Contrasena);

        Cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createuser();
            }
        });




    }

    private void createuser() {
        String nombree = nombre.getText().toString();
        String correoo = correo.getText().toString();
        String contrasenaa = contrasena.getText().toString();

        if (TextUtils.isEmpty(nombree)){
            nombre.setError("Ingrese un Nombre");
            nombre.requestFocus();
        }else if (TextUtils.isEmpty(correoo)){
            correo.setError("Ingrese un Correo");
            correo.requestFocus();
        }else if (TextUtils.isEmpty(contrasenaa)){
            contrasena.setError("Ingrese una Contraseña");
            contrasena.requestFocus();
        }else {

            mAuth.createUserWithEmailAndPassword(correoo, contrasenaa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    userID = mAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = db.collection("users").document(userID);
                    Map<String, Object> user = new HashMap<>();
                    user.put("Nombre", nombree);
                    user.put("Correo", correoo);
                    user.put("Contraseña", contrasenaa);

                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(@NonNull Void aVoid) {
                            Log.d("TAG", "onSuccess: Felicidades Usuario Creado Con Exito" + userID);
                        }

                    });
                    Toast.makeText(RegistroActivity.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistroActivity.this, LoginActivity.class));
                }
            }

            );
        }

    }
}