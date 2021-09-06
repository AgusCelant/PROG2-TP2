package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ListarContactos extends AppCompatActivity {

    private ListView ListViewContactos;
    private List<String> ListContactos = new ArrayList<String>();
    private List<String> ContactosCompletos = new ArrayList<String>();
    private ArrayAdapter<String> Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contactos);

        ListViewContactos = (ListView) findViewById(R.id.ListvContactos);
        String archivos [] = fileList();
        if(ArchivoExiste(archivos,"contactos.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("contactos.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String fila = br.readLine();

                if(fila == null) Toast.makeText(this,"No hay registros.",Toast.LENGTH_LONG).show();

                while (fila != null){
                    ContactosCompletos.add(fila);
                    String ContactoMasDatos[] = fila.split(";");
                    ListContactos.add(ContactoMasDatos[0] + " " + ContactoMasDatos[1]+ " - "+ContactoMasDatos[4]);
                    fila = br.readLine();
                }

                Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ListContactos);
                ListViewContactos.setAdapter(Adapter);
                br.close();
                archivo.close();
            }catch (IOException ex){

            }

            ListViewContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick (AdapterView < ? > adapter, View view,int position, long arg){
                                                String[] array = (String[]) ContactosCompletos.toArray(new String[ContactosCompletos.size()]);
                                                String fila = array[position];
                                                String contacto[] = fila.split(";");

                                                String nombre = contacto[0];
                                                String apellido = contacto[1];
                                                String telefono = contacto[2];
                                                String tipo_telefono = contacto[3];
                                                String email = contacto[4];
                                                String tipo_email = contacto[5];
                                                String direccion = contacto[6];
                                                String fecha_nacimiento = contacto[7];
                                                String primario = contacto[8] == "true" ? "Completo" : "Incompleto" ;
                                                String secundario = contacto[10] == "true" ? "Completo" : "Incompleto" ;
                                                String otros = contacto[12]== "true" ? "Si" : "No" ;
                                                String deporte = contacto[13]== "true" ? "Si" : "No" ;
                                                String arte = contacto[14]== "true" ? "Si" : "No" ;
                                                String musica = contacto[15]== "true" ? "Si" : "No" ;
                                                String tecnologia = contacto[16]== "true" ? "Si" : "No" ;

                                                String Resultado = "Nombre: " + nombre + "\nApellido: "+apellido + "\nTelefono:" +telefono+ "\nTipo Telefono:" +tipo_telefono+ "\nEmail:" +email
                                                        + "\nTipo Email:" +tipo_email+ "\nDireccion:" +direccion+ "\nNacimiento:" +fecha_nacimiento
                                                        + "\nPrimario:" + primario+ "\nSecundario:" + secundario+ "\nOtro:" + otros
                                                        + "\nDeporte:" + deporte+ "\nArte:" + arte
                                                        + "\nMusica:" + musica+ "\nTecnologia:" + tecnologia;

                                                Toast.makeText(getApplicationContext(), Resultado, Toast.LENGTH_LONG).show();
                                            }
                                        }
            );


            //
        }else{
            Toast.makeText(this,"No existe el archivo",Toast.LENGTH_LONG).show();
        }
    }

    private boolean ArchivoExiste(String archivos [],String NombreArchivo){
        for(int i=0;i < archivos.length; i++ )
            if(NombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }



}