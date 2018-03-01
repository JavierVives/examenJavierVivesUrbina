package com.example.javi.examenjaviervivesurbina;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Llamar extends AppCompatActivity {

    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE = 1;
    ImageButton imageButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamar);

        // ACTIVITY LLAMAR

        imageButton2=(ImageButton)findViewById(R.id.imageButton2);

    }


    public void llamar(View v){
        //Siguiendo los pasos del ejercicio EnviarSMS para probar
        String numeTel=""+651496875; //Numero prueba

        try{
            //Verificamos permisos necesarios para enviar SMS
            int permisoLlamada = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

            // Esta linea de codigo pide los permisos necesarios!
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MAKE_CALL_PERMISSION_REQUEST_CODE);

            if (comprobarPermiso(Manifest.permission.CALL_PHONE)) {
                String dial = "tel:" + numeTel; //Se tiene que poner literalmente esto
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            } else {
                Toast.makeText(getApplicationContext(), "Permiso de llamada denegado", Toast.LENGTH_SHORT).show();
            }


        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "La llamada no ha sido realizada, verifique persmisos o datos" + e.getMessage().toString(),Toast.LENGTH_LONG ).show();
        }
    }

    private boolean comprobarPermiso(String permission) {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case MAKE_CALL_PERMISSION_REQUEST_CODE :
                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    imageButton2.setEnabled(true);
                    Toast.makeText(this, "Para llamar al número indicado, pulse el botón de llamada", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }


}
