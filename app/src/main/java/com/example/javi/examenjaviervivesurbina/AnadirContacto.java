package com.example.javi.examenjaviervivesurbina;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AnadirContacto extends AppCompatActivity {


    private EditText etNombre, etTelefono;
    private Button btnGuardar, btnCancelar;
    String texto = "";
    String nomArchivo = "contactos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_contacto);

        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean encontrado = false;
                String[] archivos = fileList();
                for (int i = 0; i < archivos.length; i++) {
                    if (nomArchivo.equals(archivos[i]))
                        encontrado = true;
                }

                if (encontrado) {


                    try {
                        InputStreamReader archivo = new InputStreamReader(openFileInput(nomArchivo));
                        BufferedReader br = new BufferedReader(archivo);

                        String linea = br.readLine();

                        while (linea != null) {
                            texto += linea + "\n";
                            linea = br.readLine();
                        }

                        br.close();


                        String nombreyContacto = texto + etNombre.getText().toString() + "\n" + etTelefono.getText().toString() + "\n";
                        OutputStreamWriter archivoSalida = new OutputStreamWriter(openFileOutput("contactos", Activity.MODE_PRIVATE));
                        archivoSalida.write(nombreyContacto);
                        archivoSalida.flush();
                        archivoSalida.close();

                        // Mesaje para el usuario
                        Toast.makeText(getApplicationContext(), "Contacto insertado correctamente!", Toast.LENGTH_SHORT);


                        archivo.close();


                    } catch (FileNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "NO SE HA ENCONTRADO EL ARCHIVO", Toast.LENGTH_SHORT);
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "ERROR ENTRADA SALIDA!", Toast.LENGTH_SHORT);
                    }
                }else{

                    try {
                        String nombreyContacto = etNombre.getText().toString() + "\n" + etTelefono.getText().toString() + "\n";
                        OutputStreamWriter archivoSalidaNuevo = new OutputStreamWriter(openFileOutput("contactos", Activity.MODE_PRIVATE));
                        archivoSalidaNuevo.write(nombreyContacto);
                        archivoSalidaNuevo.flush();
                        archivoSalidaNuevo.close();

                        // Mesaje para el usuario
                        Toast.makeText(getApplicationContext(), "Contacto insertado correctamente!", Toast.LENGTH_SHORT);


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                etNombre.setText("");
                etTelefono.setText("");
            }
        });


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}

