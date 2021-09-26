package com.vana.blue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hp.bluetoothjhr.BluetoothJhr;

public class ConexionBlu extends AppCompatActivity {

    BluetoothJhr bluetoothJhr2;
    Button Enviar;
    TextView Console;
    EditText TextoEnviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion_blu);

        bluetoothJhr2 = new BluetoothJhr(MainActivity.class,this);
        Enviar = findViewById(R.id.Enviar);
        Console = findViewById(R.id.Console);
        TextoEnviar = findViewById(R.id.TextoEnviar);

        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Mensaje = TextoEnviar.getText().toString();
                bluetoothJhr2.Tx(Mensaje);
                bluetoothJhr2.ResetearRx();

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Delay();
                    Console.post(new Runnable() {
                        @Override
                        public void run() {
                            if(bluetoothJhr2.Rx()!=null && bluetoothJhr2.Rx()!="null" && bluetoothJhr2.Rx().equalsIgnoreCase("null") && bluetoothJhr2.Rx()!=""){
                                String Dato = bluetoothJhr2.Rx();
                                Console.setText(Dato);

                            }
                        }
                    });
                }
            }
        }).start();

    }

    @Override
    public void onResume(){
        super.onResume();
        bluetoothJhr2.ConectaBluetooth();
    }

    @Override
    public void onPause(){
        super.onPause();
        bluetoothJhr2.CierraConexion();
    }
    private void Delay(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
