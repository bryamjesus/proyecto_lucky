package com.example.proyectolucky;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectolucky.db.DbUsuario;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtApellido, txtTelefono, txtCorreo, txtPass;
    Button btnGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.createTxtNombre);
        txtApellido = findViewById(R.id.createTxtApellido);
        txtTelefono = findViewById(R.id.createTxtTelefono);
        txtCorreo = findViewById(R.id.createTxtCorreo);
        txtPass = findViewById(R.id.createTxtPassword);

        btnGuardar = findViewById(R.id.createBtnAgregar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbUsuario dbUsuario = new DbUsuario(NuevoActivity.this);
                long id = dbUsuario.insertarContacto(txtNombre.getText().toString(),txtApellido.getText().toString(),txtTelefono.getText().toString(),txtCorreo.getText().toString(),txtPass.getText().toString());

                if(id > 0){
                    Toast.makeText(NuevoActivity.this, "REGISTRO EXITOSO",Toast.LENGTH_LONG).show();
                    limpiar();
                }else {
                    Toast.makeText(NuevoActivity.this, "FALLO AL REGISTRAR",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtPass.setText("");
    }
}