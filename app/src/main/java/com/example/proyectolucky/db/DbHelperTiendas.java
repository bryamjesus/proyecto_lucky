package com.example.proyectolucky.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelperTiendas extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NOMBRE = "Tiendas.db";
    public static final String TABLE_USUARIO = "t_tiendas";
    public static final String TABLE_PRODUCTOS = "t_productos";

    public DbHelperTiendas(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USUARIO+"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "direccion_titulo TEXT NOT NULL," +
                "direccion_real TEXT NOT NULL," +
                "codigo TEXT NOT NULL)");

        sqLiteDatabase.execSQL("INSERT INTO t_tiendas (direccion_titulo,direccion_real,codigo) VALUES (\"Yosly Amali Seguilar\",\"Real S/N Urb: ESQ 10 NOVIEMBRE\",\"409183\"),(\"Metro Alfonso Ugarte\",\"Av. Alfonso Ugarte\",\"409184\"),(\"Tottus Zorritos\",\"Av.Colonial 1520\",\"409185\")");

        sqLiteDatabase.execSQL("CREATE TABLE t_productos (" +
                "idproducto INTEGER PRIMARY KEY AUTOINCREMENT," +
                "producto TEXT NOT NULL," +
                "pCosto TEXT NOT NULL," +
                "pCostoXMayor TEXT NOT NULL," +
                "stock TEXT NOT NULL," +
                "idTienda INTEGER," +
                "FOREIGN KEY(idTienda) REFERENCES t_tiendas(id))");

        sqLiteDatabase.execSQL("INSERT INTO t_productos (producto,pCosto, pCostoXMayor, stock,idTienda ) VALUES \n" +
                "(\"Aceite Idealx12 Bot\",\"42\",\"45.23\",\"120\",1),\n" +
                "(\"Aceite Idealx12 Bot\",\"42\",\"45.23\",\"120\",1),\n" +
                "(\"Aceite Idealx12 Bot\",\"42\",\"45.23\",\"120\",1),\n" +
                "(\"Aceite Idealx12 Bot\",\"42\",\"45.23\",\"120\",1),\n" +
                "(\"Aceite Idealx12 Bot\",\"42\",\"45.23\",\"120\",1)");

        sqLiteDatabase.execSQL("INSERT INTO t_productos (producto,pCosto, pCostoXMayor, stock,idTienda ) VALUES \n" +
                "(\"Aceite Idealx12 Bot\",\"42\",\"45.23\",\"120\",2),\n" +
                "(\"Aceite Idealx12 Bot\",\"42\",\"45.23\",\"120\",2),\n" +
                "(\"Aceite Idealx12 Bot\",\"42\",\"45.23\",\"120\",2),\n" +
                "(\"Aceite Idealx12 Bot\",\"42\",\"45.23\",\"120\",2),\n" +
                "(\"Aceite Idealx12 Bot\",\"42\",\"45.23\",\"120\",2)");

        sqLiteDatabase.execSQL("INSERT INTO t_productos (producto,pCosto, pCostoXMayor, stock,idTienda ) VALUES \n" +
                "(\"Aceite Idealx12 Bot\",\"42\",\"45.23\",\"120\",3),\n" +
                "(\"Aceite Idealx12 Bot\",\"42\",\"45.23\",\"120\",3),\n" +
                "(\"Aceite Idealx12 Bot\",\"42\",\"45.23\",\"120\",3),\n" +
                "(\"Aceite Idealx12 Bot\",\"42\",\"45.23\",\"120\",3),\n" +
                "(\"Aceite Idealx12 Bot\",\"42\",\"45.23\",\"120\",3)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " +TABLE_USUARIO);
        sqLiteDatabase.execSQL("DROP TABLE " +TABLE_PRODUCTOS);
        onCreate(sqLiteDatabase);
    }
}
