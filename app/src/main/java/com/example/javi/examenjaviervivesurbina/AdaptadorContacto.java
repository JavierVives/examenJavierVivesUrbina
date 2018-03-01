package com.example.javi.examenjaviervivesurbina;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class AdaptadorContacto extends BaseAdapter {

    private ArrayList<Contacto> contactos;
    private Context context;

    public AdaptadorContacto(Context context, ArrayList<Contacto> contactos) {
        this.contactos = contactos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Object getItem(int i) {
        return contactos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View vista = view;
        LayoutInflater inflate = LayoutInflater.from(context);
        vista = inflate.inflate(R.layout.item_listview, null);

        TextView tvNom = vista.findViewById(R.id.tvNom);
        TextView tvTel = vista.findViewById(R.id.tvTel);
        ImageView iBSMS = vista.findViewById(R.id.iBSMS);
        ImageView iBllamar = vista.findViewById(R.id.iBLlamar);
        ImageView iBEliminar = vista.findViewById(R.id.iBEliminar);


        tvNom.setText(contactos.get(i).getNombre().toString());
        tvTel.setText(contactos.get(i).getTelefono().toString());


        iBSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.getContext().startActivity(new Intent(view.getContext(), MensajeSMS.class));

            }
        });

        iBllamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.getContext().startActivity(new Intent(view.getContext(), Llamar.class));

            }
        });

        return vista;
    }
}
