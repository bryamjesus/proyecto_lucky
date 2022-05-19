package com.example.proyectolucky.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyectolucky.entidades.Usuarios;

public class DbUsuario extends DbHelper{
    Context context;

    public DbUsuario(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarContacto(String nombre, String apellido, String telefono, String correo, String password){

        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre",nombre);
            values.put("apellido",apellido);
            values.put("telefono",telefono);
            values.put("correo",correo);
            values.put("password",password);

            id = db.insert(TABLE_USUARIO, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public Usuarios verUsuario(String correo, String password) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Usuarios usuarios = null;
        Cursor cursorUsuarios;

        cursorUsuarios = db.rawQuery("SELECT * FROM " + TABLE_USUARIO + " WHERE correo = '" + correo + "' AND password = '"+password +"' LIMIT 1", null);

        if (cursorUsuarios.moveToFirst()) {
            usuarios = new Usuarios();
            usuarios.setId(cursorUsuarios.getInt(0));
            usuarios.setNombre(cursorUsuarios.getString(1));
            usuarios.setApellido(cursorUsuarios.getString(2));
            usuarios.setTelefono(cursorUsuarios.getString(3));
            usuarios.setCorreo(cursorUsuarios.getString(4));
            usuarios.setPassword(cursorUsuarios.getString(5));
        }

        cursorUsuarios.close();

        return usuarios;
    }

    public Usuarios verInfoUsuario(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Usuarios usuarios = null;
        Cursor cursorUsuarios;

        cursorUsuarios = db.rawQuery("SELECT * FROM " + TABLE_USUARIO + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorUsuarios.moveToFirst()) {
            usuarios = new Usuarios();
            usuarios.setId(cursorUsuarios.getInt(0));
            usuarios.setNombre(cursorUsuarios.getString(1));
            usuarios.setApellido(cursorUsuarios.getString(2));
            usuarios.setTelefono(cursorUsuarios.getString(3));
            usuarios.setCorreo(cursorUsuarios.getString(4));
            usuarios.setPassword(cursorUsuarios.getString(5));
        }

        cursorUsuarios.close();

        return usuarios;
    }
}
