package com.example.javi.examenjaviervivesurbina;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MensajeSMS extends AppCompatActivity {

    EditText etSMS;
    ImageButton iBSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje_sms);

        etSMS=(EditText)findViewById(R.id.etSMS);
        iBSMS=(ImageButton)findViewById(R.id.iBSMS);

        // ACTIVITY SMS

    }

    public void enviarMensaje(View v){
        //Siguiendo los pasos del ejercicio EnviarSMS para probar
        String numTel=""+651496875; //Numero prueba
        String datosCompletos="Usuario -- Libreria -- " + etSMS.getText().toString();

        try{
            //Verificamos permisos necesarios para enviar SMS
            int permisoEnvioSMS = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

            if (permisoEnvioSMS != PackageManager.PERMISSION_GRANTED){

                Toast.makeText(getApplicationContext(), "No tiene permiso para enviar SMS", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 225);
            }
            else{ //Nada//
            }

            SmsManager sms=SmsManager.getDefault();
            sms.sendTextMessage(numTel ,null, datosCompletos, null, null);
            Toast.makeText(getApplicationContext(), "Mensaje enviado con éxito", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Mensaje no enviado, verifique persmisos o datos" + e.getMessage().toString(),Toast.LENGTH_LONG ).show();
        }
    }
}
