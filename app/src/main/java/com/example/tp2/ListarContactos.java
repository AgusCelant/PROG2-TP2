package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
                    String ContactoMasDatos[] = fila.split(";");
                    ListContactos.add(ContactoMasDatos[0] + " " + ContactoMasDatos[1]+ " - "+ContactoMasDatos[4]);
                    fila = br.readLine();
                }
                String[] itemsArray = new String[ListContactos.size()];
                itemsArray = ListContactos.toArray(itemsArray);

                String algo [] = {"HOLA","CHAU"};
                Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,algo);
                ListViewContactos.setAdapter(Adapter);
                br.close();
                archivo.close();
            }catch (IOException ex){

            }
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