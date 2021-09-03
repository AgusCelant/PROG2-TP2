package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AgregarContacto extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);
    }

    public void MasDatos(View view){
        Intent masDatos = new Intent(this, AgregarContacto_MasDatos.class);
        startActivity(masDatos);
    }
}