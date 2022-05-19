package com.example.proyectolucky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectolucky.db.DbTiendas;
import com.example.proyectolucky.entidades.Tiendas;

public class VerActivity extends AppCompatActivity {

    TextView verTxtDireccion, verTxtDireccionReal;
    ImageButton btnFoto;
    Button btnVisitar;

    Tiendas tiendas;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        verTxtDireccion = findViewById(R.id.verTxtDireccion);
        verTxtDireccionReal = findViewById(R.id.verTxtDireccionReal);
        btnFoto = findViewById(R.id.btnFoto);
        btnVisitar = findViewById(R.id.btnVisitar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else {
                id = extras.getInt("IdTienda");
            }
        }else {
            id = (int) savedInstanceState.getSerializable("IdTienda");
        }

        Toast.makeText(VerActivity.this, "ID"+id ,Toast.LENGTH_LONG).show();
        DbTiendas dbTiendas = new DbTiendas(VerActivity.this);
        tiendas = dbTiendas.verContacto(id);
        if(tiendas != null){
            verTxtDireccionReal.setText(tiendas.getDireccionReal());
            verTxtDireccion.setText(tiendas.getDireccion());
        }

        btnVisitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, VisitarActivity.class);
                intent.putExtra("IdVisitar",id);
                context.startActivity(intent);
            }
        });


        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });
    }

    private void abrirCamara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityIfNeeded(intent,1);
            //startActivityForResult(intent, 1);
        }
    }

    protected void onActivityResult(int requesCode, int resultCode, Intent data) {
        super.onActivityResult(requesCode, resultCode, data);
        if (requesCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            btnFoto.setImageBitmap(imgBitmap);
        }
    }
}