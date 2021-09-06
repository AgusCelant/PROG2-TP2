package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class AgregarContacto_MasDatos extends AppCompatActivity {

    private RadioButton rbPrimarioIncompleto, rbPrimarioCompleto, rbSecundarioIncompleto, rbSecundarioCompleto, rbOtros;
    private CheckBox chDeporte, chArte, chMusica, chTecnologia;
    private Switch info;
    private ArrayList<String> contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto_mas_datos);

        rbPrimarioCompleto = (RadioButton)findViewById(R.id.rbPrimarioCompleto);
        rbPrimarioIncompleto = (RadioButton)findViewById(R.id.rbPrimarioIncompleto);
        rbSecundarioCompleto = (RadioButton)findViewById(R.id.rbSecundarioCompleto);
        rbSecundarioIncompleto = (RadioButton)findViewById(R.id.rbSecundarioIncompleto);
        rbOtros = (RadioButton)findViewById(R.id.rbOtros);
        info = (Switch)findViewById(R.id.swRecibirInfo) ;

        chDeporte = (CheckBox)findViewById(R.id.ckDeporte);
        chArte = (CheckBox)findViewById(R.id.chArte);
        chTecnologia = (CheckBox)findViewById(R.id.chTecnologia);
        chMusica = (CheckBox)findViewById(R.id.chMusica);
    }

    public void Guardar(View view){
        String archivos [] = fileList();

        if(ArchivoExiste(archivos, "contactos.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("contactos.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                //String bitacoraCompleta = "";

                while(linea != null){
                    contactos.add(linea);
                    //bitacoraCompleta = bitacoraCompleta + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();

            }catch (IOException e){

            }
        }

        GuardarArchivo();

        Intent listar = new Intent(this, ListarContactos.class);

        startActivity(listar);
    }

    private boolean ArchivoExiste(String archivos [], String NombreArchivo){
        for(int i = 0; i < archivos.length; i++)
            if(NombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }

    private void GuardarArchivo(){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("contactos.txt", Activity.MODE_PRIVATE));
            String nombre = getIntent().getStringExtra("nombre");
            String apellido = getIntent().getStringExtra("apellido");
            String telefono = getIntent().getStringExtra("telefono");
            String tipo_telefono = getIntent().getStringExtra("tipo_telefono");
            String email = getIntent().getStringExtra("email");
            String tipo_email = getIntent().getStringExtra("tipo_email");
            String direccion = getIntent().getStringExtra("direccion");
            String fecha_nacimiento = getIntent().getStringExtra("fecha_nacimiento");

            String contacto = nombre+";"+apellido+";"+telefono+";"+tipo_telefono+";"+email+";"+tipo_email+";"+direccion+";"+fecha_nacimiento;

            String primarioInc = rbPrimarioIncompleto.isChecked() ? "true" : "false";
            String primarioComp = rbSecundarioIncompleto.isChecked() ? "true" : "false";
            String secundarioInc = rbSecundarioCompleto.isChecked() ? "true" : "false";
            String secundarioComp = rbSecundarioIncompleto.isChecked() ? "true" : "false";
            String otros = rbOtros.isChecked() ? "true" : "false";
            String deporte = chDeporte.isChecked() ? "true" : "false";
            String arte = chArte.isChecked() ? "true" : "false";
            String musica = chMusica.isChecked() ? "true" : "false";
            String tecnologia = chTecnologia.isChecked() ? "true" : "false";

            contacto = contacto+";"+primarioInc+";"+primarioComp+";"+secundarioInc+";"+secundarioComp+";"+otros+";"+deporte+";"+arte+";"+musica+";"+tecnologia;
            archivo.write(contacto+"\n");
            archivo.flush();
            archivo.close();
            Toast.makeText(this, "Contacto guardado correctamente", Toast.LENGTH_SHORT).show();
        }catch (IOException e){

        }
    }

}