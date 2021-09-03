package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ejecutar_AgregarContacto(View view){
        Intent i = new Intent(this, AgregarContacto.class);
        startActivity(i);
    }

    public void ejecutar_ListarContactos(View view){
        Intent i = new Intent(this, ListarContactos.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_principal, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem opcion){
    int id = opcion.getItemId();

    if(id == R.id.Agregar){
        ejecutar_AgregarContacto(null);
    return true;
    }
    if(id == R.id.Listar){
        ejecutar_ListarContactos(null);
    return true;
    }

    return super.onOptionsItemSelected(opcion);
    }
}