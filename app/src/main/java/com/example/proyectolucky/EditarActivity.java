package com.example.proyectolucky;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectolucky.db.DbTiendas;
import com.example.proyectolucky.entidades.Productos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    EditText editTxtProducto, editTxtCosto, editTxtCostoXMayor, editTxtStock;
    Button editBtnGuardar, editBtnRegresar;
    Productos productos;

    FloatingActionButton fabEditar;
    int id = 0;
    boolean correcto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("IdProducto");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("IdProducto");
        }

        editTxtProducto = findViewById(R.id.editTxtProducto);
        editTxtCosto = findViewById(R.id.editTxtCosto);
        editTxtCostoXMayor = findViewById(R.id.editTxtCostoXMayor);
        editTxtStock = findViewById(R.id.editTxtStock);
        editBtnGuardar = findViewById(R.id.editBtnGuardar);
        editBtnRegresar = findViewById(R.id.editBtnRegresar);

        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);

        final DbTiendas dbTiendas = new DbTiendas(EditarActivity.this);
        productos = dbTiendas.verProducto(id);

        if (productos != null) {
            editTxtProducto.setText(productos.getProducto());
            editTxtCosto.setText(productos.getpCosto());
            editTxtCostoXMayor.setText(productos.getpCostoXMayor());
            editTxtStock.setText(productos.getStock());
        }

        editBtnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editTxtProducto.getText().toString().equals("") && !editTxtCosto.getText().toString().equals("") && !editTxtCostoXMayor.getText().toString().equals("") && !editTxtStock.getText().toString().equals("")) {
                    correcto = dbTiendas.editarContacto(id, editTxtProducto.getText().toString(), editTxtCosto.getText().toString(), editTxtCostoXMayor.getText().toString(), editTxtStock.getText().toString());
                    if (correcto) {
                        Toast.makeText(EditarActivity.this, "Registro Modificado", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR Registro Modificado", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        editBtnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verRegistro();
            }
        });


    }
    private void verRegistro(){
        Intent intent = new Intent(this, EditarProductoActivity.class);
        intent.putExtra("IdProducto", id);
        startActivity(intent);
    }

}
