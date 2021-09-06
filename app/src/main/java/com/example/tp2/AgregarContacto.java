package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AgregarContacto extends AppCompatActivity {

    private EditText etNombre, etApellido, etTelefono, etMail, etDireccion, etFechaNac;
    private Spinner spinner_tel, spinner_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);

        etNombre = (EditText)findViewById(R.id.etNombre);
        etApellido = (EditText)findViewById(R.id.etApellido);
        etTelefono = (EditText)findViewById(R.id.etTelefono);
        etMail = (EditText)findViewById(R.id.etMail);
        etDireccion = (EditText)findViewById(R.id.etDireccion);
        etFechaNac = (EditText)findViewById(R.id.etFechaNac);

        spinner_tel = (Spinner) findViewById(R.id.spinner_tel);
        spinner_email = (Spinner) findViewById(R.id.spinner_email);
        String [] opciones = {"Casa", "Trabajo", "Móvil"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item_tp, opciones);
        ArrayAdapter<String> adapterEmail = new ArrayAdapter<>(this, R.layout.spinner_item_tp, opciones);
        spinner_tel.setAdapter(adapter);
        spinner_email.setAdapter(adapterEmail);
    }

    public void MasDatos(View view){
        Intent masDatos = new Intent(this, AgregarContacto_MasDatos.class);
        masDatos.putExtra("nombre",etNombre.getText().toString());
        masDatos.putExtra("apellido",etApellido.getText().toString());
        masDatos.putExtra("telefono",etTelefono.getText().toString());
        masDatos.putExtra("email",etMail.getText().toString());
        masDatos.putExtra("direccion",etDireccion.getText().toString());
        masDatos.putExtra("fecha_nacimiento",etFechaNac.getText().toString());
        
        String tel = spinner_tel.getSelectedItem().toString();
        if(!tel.isEmpty()){
            masDatos.putExtra("tipo_telefono",tel);
        }
        String email = spinner_email.getSelectedItem().toString();
        if(!email.isEmpty()){
            masDatos.putExtra("tipo_email", email);
        }
        
        startActivity(masDatos);
    }
}