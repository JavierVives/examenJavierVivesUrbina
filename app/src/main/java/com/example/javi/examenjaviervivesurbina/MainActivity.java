package com.example.javi.examenjaviervivesurbina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAnadirContacto, btnListarContacto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAnadirContacto = findViewById(R.id.btnAnadirContacto);
        btnListarContacto = findViewById(R.id.btnListarContacto);

        btnAnadirContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AnadirContacto.class);
                startActivity(i);
            }
        });
        btnListarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ListarContacto.class);
                startActivity(i);
            }
        });

    }
}
