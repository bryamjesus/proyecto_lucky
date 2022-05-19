package com.example.proyectolucky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectolucky.db.DbUsuario;
import com.example.proyectolucky.entidades.Usuarios;

public class MainActivity extends AppCompatActivity {

    Button loginBtnIngresar,loginBtnCrear;
    EditText loginTxtUsuario, loginTxtPassword;

    Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "REGISTRE USUARIOS, PORFAVOR",Toast.LENGTH_LONG).show();

        loginBtnIngresar = findViewById(R.id.loginBtnIngresar);
        loginBtnCrear = findViewById(R.id.loginBtnCreate);
        loginTxtUsuario = findViewById(R.id.loginTxtUsuario);
        loginTxtPassword = findViewById(R.id.loginTxtPassword);

        loginBtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbUsuario dbUsuario = new DbUsuario(MainActivity.this);
                usuarios = dbUsuario.verUsuario(loginTxtUsuario.getText().toString(),loginTxtPassword.getText().toString());

                if(usuarios != null){
                    Context context = view.getContext();
                    Toast.makeText(MainActivity.this, "INICIO DE SESION EXITOSO USUARIO:'"+loginTxtUsuario.getText().toString()+"' ",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
                    intent.putExtra("ID",usuarios.getId());
                    context.startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "FALLO AL INICIAR SESION",Toast.LENGTH_LONG).show();
                }
            }
        });

        loginBtnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NuevoActivity.class);
                startActivity(intent);
            }
        });

    }



}