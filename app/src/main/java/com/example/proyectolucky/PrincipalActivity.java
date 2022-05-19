package com.example.proyectolucky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectolucky.db.DbUsuario;
import com.example.proyectolucky.entidades.Usuarios;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectolucky.databinding.ActivityPrincipalBinding;

public class PrincipalActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityPrincipalBinding binding;
    int id =0;
    Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else {
                id =extras.getInt("ID");
            }
        }else {
            id =(int) savedInstanceState.getSerializable("ID");
        }

        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarPrincipal.toolbar);
        binding.appBarPrincipal.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }


    @Override
    protected void onStart() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        super.onStart();
        getUsuario(id);
    }

    private void getUsuario(int id){
        final View vistaHeader = binding.navView.getHeaderView(0);
        final TextView principalTxtNombre = vistaHeader.findViewById(R.id.principalTxtNombre);
        final TextView principalTxtCorreo = vistaHeader.findViewById(R.id.principalTxtCorreo);

        DbUsuario dbUsuario = new DbUsuario(PrincipalActivity.this);
        usuarios = dbUsuario.verInfoUsuario(id);
        if(usuarios != null){
            String nombreCompleto = usuarios.getNombre().toUpperCase() +" "+usuarios.getApellido().toUpperCase();
            principalTxtCorreo.setText(usuarios.getCorreo());
            principalTxtNombre.setText(nombreCompleto);
        }

        Toast.makeText(PrincipalActivity.this,"El id es "+id ,Toast.LENGTH_LONG).show();


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}