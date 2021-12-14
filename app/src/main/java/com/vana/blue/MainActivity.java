package com.vana.blue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hp.bluetoothjhr.BluetoothJhr;

public class MainActivity extends AppCompatActivity {

    ListView ListaDispositivos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ListaDispositivos = findViewById(R.id.ListaDispositivos);

        final BluetoothJhr bluetoothJhr = new BluetoothJhr(this, ListaDispositivos);
        bluetoothJhr.EncenderBluetooth();

        ListaDispositivos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                bluetoothJhr.Disp_Seleccionado(view,position,ConexionBlu.class);
            }
        });
    }
}
