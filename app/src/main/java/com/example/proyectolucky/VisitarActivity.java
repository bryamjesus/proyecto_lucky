package com.example.proyectolucky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.proyectolucky.adaptadores.ListaProductosAdapter;
import com.example.proyectolucky.db.DbTiendas;
import com.example.proyectolucky.entidades.Productos;
import com.example.proyectolucky.ui.home.HomeFragment;

import java.util.ArrayList;

public class VisitarActivity extends AppCompatActivity {

    RecyclerView recyclerProductos;
    ArrayList<Productos> listaArrayProductos;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else {
                id = extras.getInt("IdVisitar");
            }
        }else {
            id = (int) savedInstanceState.getSerializable("IdVisitar");
        }

        listaArrayProductos = new ArrayList<>();
        recyclerProductos =findViewById(R.id.recyclerProductos);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        DbTiendas dbTiendas = new DbTiendas(VisitarActivity.this);

        ListaProductosAdapter adapter = new ListaProductosAdapter(dbTiendas.mostrarProductos(id));

        recyclerProductos.setAdapter(adapter);

        Toast.makeText(VisitarActivity.this, "ID De la tienda "+id ,Toast.LENGTH_LONG).show();

    }

    //@Override protected void onResume() { super.onResume(); this.onCreate(null); }
}