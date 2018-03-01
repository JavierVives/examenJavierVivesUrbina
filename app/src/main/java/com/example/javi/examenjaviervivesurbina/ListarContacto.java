package com.example.javi.examenjaviervivesurbina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListarContacto extends AppCompatActivity {

    private ListView lvContactos;
    private ArrayList<Contacto> contactos;
    private ImageButton iBSMS, iBllamar, iBEliminar;
    private final String nomArchivo = "contactos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contacto);

        contactos = new ArrayList<Contacto>();

        lvContactos = findViewById(R.id.lvContactos);

        contactos = recuperarContacto();

        Toast.makeText(getApplicationContext(), String.valueOf(contactos.size()), Toast.LENGTH_SHORT).show();

        AdaptadorContacto adaptador = new AdaptadorContacto(this, contactos);
        lvContactos.setAdapter(adaptador);

        lvContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {




            }
        });


    }

    public ArrayList<Contacto> recuperarContacto(){
        ArrayList<Contacto> contactos = new ArrayList<Contacto>();

        boolean encontrado = false;
        String[] archivos = fileList();
        for(int i = 0; i < archivos.length; i++){
            if(nomArchivo.equals(archivos[i]))
                encontrado = true;

            if(encontrado){
                try {
                    InputStreamReader archivo = new InputStreamReader(openFileInput(nomArchivo));
                    BufferedReader br = new BufferedReader(archivo);

                    String nombre = br.readLine();
                    String telefono = br.readLine();
                    while(nombre != null && telefono != null ){
                        //Toast.makeText(getApplicationContext(), nombre + "   " + telefono, Toast.LENGTH_SHORT).show();
                        contactos.add(new Contacto(nombre, telefono));
                        nombre = br.readLine();
                        telefono = br.readLine();
                    }

                    br.close();
                    archivo.close();


                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "NO SE HA ENCONTRADO EL ARCHIVO", Toast.LENGTH_SHORT);
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "ERROR ENTRADA SALIDA!", Toast.LENGTH_SHORT);
                }
            }
        }
        return contactos;
    }
}
