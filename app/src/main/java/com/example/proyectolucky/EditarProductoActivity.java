package com.example.proyectolucky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectolucky.db.DbTiendas;
import com.example.proyectolucky.entidades.Productos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarProductoActivity extends AppCompatActivity {

    EditText editTxtProducto,editTxtCosto,editTxtCostoXMayor,editTxtStock;
    Button editBtnGuardar, editBtnRegresar;
    Productos productos;

    FloatingActionButton fabEditar;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else {
                id = extras.getInt("IdProducto");
            }
        }else {
            id = (int) savedInstanceState.getSerializable("IdProducto");
        }

        editTxtProducto = findViewById(R.id.editTxtProducto);
        editTxtCosto = findViewById(R.id.editTxtCosto);
        editTxtCostoXMayor = findViewById(R.id.editTxtCostoXMayor);
        editTxtStock = findViewById(R.id.editTxtStock);
        editBtnGuardar = findViewById(R.id.editBtnGuardar);
        editBtnRegresar = findViewById(R.id.editBtnRegresar);
        fabEditar = findViewById(R.id.fabEditar);
        editBtnGuardar.setVisibility(View.INVISIBLE);

        DbTiendas dbTiendas = new DbTiendas(EditarProductoActivity.this);
        productos = dbTiendas.verProducto(id);

        if(productos != null){
            editTxtProducto.setText(productos.getProducto());
            editTxtCosto.setText(productos.getpCosto());
            editTxtCostoXMayor.setText(productos.getpCostoXMayor());
            editTxtStock.setText(productos.getStock());

            editTxtProducto.setInputType(InputType.TYPE_NULL);
            editTxtCosto.setInputType(InputType.TYPE_NULL);
            editTxtCostoXMayor.setInputType(InputType.TYPE_NULL);
            editTxtStock.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarProductoActivity.this, EditarActivity.class);
                intent.putExtra("IdProducto", id);
                startActivity(intent);

            }
        });

        editBtnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarProductoActivity.this, VisitarActivity.class);
                intent.putExtra("IdVisitar", productos.getIdTienda());
                startActivity(intent);
            }
        });
    }
}