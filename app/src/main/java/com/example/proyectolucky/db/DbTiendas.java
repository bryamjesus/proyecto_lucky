package com.example.proyectolucky.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyectolucky.entidades.Productos;
import com.example.proyectolucky.entidades.Tiendas;

import java.util.ArrayList;

public class DbTiendas extends DbHelperTiendas{

    Context context;
    public DbTiendas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public ArrayList<Tiendas> mostrarTiendas() {

        DbHelperTiendas dbHelperTiendas = new DbHelperTiendas(context);
        SQLiteDatabase db = dbHelperTiendas.getWritableDatabase();

        ArrayList<Tiendas> listaTiendas = new ArrayList<>();
        Tiendas tienda = null;
        Cursor cursorTienda = null;

        cursorTienda = db.rawQuery("SELECT * FROM " + TABLE_USUARIO + " ORDER BY id ASC", null);

        if (cursorTienda.moveToFirst()) {
            do {
                tienda = new Tiendas();
                tienda.setId(cursorTienda.getInt(0));
                tienda.setDireccion(cursorTienda.getString(1));
                tienda.setDireccionReal(cursorTienda.getString(2));
                tienda.setCodigo(cursorTienda.getString(3));
                listaTiendas.add(tienda);
            } while (cursorTienda.moveToNext());
        }

        cursorTienda.close();

        return listaTiendas;
    }

    public Tiendas verContacto(int id) {

        DbHelperTiendas dbHelperTiendas = new DbHelperTiendas(context);
        SQLiteDatabase db = dbHelperTiendas.getWritableDatabase();

        Tiendas tienda = null;
        Cursor cursorTienda = null;

        cursorTienda = db.rawQuery("SELECT * FROM " + TABLE_USUARIO + " WHERE id = "+ id+ " LIMIT 1", null);

        if (cursorTienda.moveToFirst()) {
            tienda = new Tiendas();
            tienda.setId(cursorTienda.getInt(0));
            tienda.setDireccion(cursorTienda.getString(1));
            tienda.setDireccionReal(cursorTienda.getString(2));
            tienda.setCodigo(cursorTienda.getString(3));
        }

        cursorTienda.close();

        return tienda;
    }

    public ArrayList<Productos> mostrarProductos(int id) {

        DbHelperTiendas dbHelperTiendas = new DbHelperTiendas(context);
        SQLiteDatabase db = dbHelperTiendas.getWritableDatabase();

        ArrayList<Productos> listaProductos = new ArrayList<>();
        Productos productos = null;
        Cursor cursorProducto = null;

        cursorProducto = db.rawQuery("SELECT idproducto, producto, pCosto, pCostoXMayor, stock from "+TABLE_PRODUCTOS+" INNER JOIN "+TABLE_USUARIO+" ON t_productos.idTienda = t_tiendas.id WHERE idTienda = "+id+" ", null);


        if (cursorProducto.moveToFirst()) {
            do {
                productos = new Productos();
                //Toast.makeText(context,cursorProducto.getString(2),Toast.LENGTH_LONG).show();
                productos.setIdproducto(cursorProducto.getInt(0));
                productos.setProducto(cursorProducto.getString(1));
                productos.setpCosto(cursorProducto.getString(2));
                productos.setpCostoXMayor(cursorProducto.getString(3));
                productos.setStock(cursorProducto.getString(4));
                listaProductos.add(productos);
            } while (cursorProducto.moveToNext());
        }

        cursorProducto.close();

        return listaProductos;
    }

    public Productos verProducto(int id) {

        DbHelperTiendas dbHelperTiendas = new DbHelperTiendas(context);
        SQLiteDatabase db = dbHelperTiendas.getWritableDatabase();

        Productos producto = null;
        Cursor cursorProducto = null;

        cursorProducto = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS + " WHERE idproducto = "+ id+ " LIMIT 1", null);

        if (cursorProducto.moveToFirst()) {
            producto = new Productos();
            producto.setIdproducto(cursorProducto.getInt(0));
            producto.setProducto(cursorProducto.getString(1));
            producto.setpCosto(cursorProducto.getString(2));
            producto.setpCostoXMayor(cursorProducto.getString(3));
            producto.setStock(cursorProducto.getString(4));
            producto.setIdTienda(cursorProducto.getInt(5));
        }

        cursorProducto.close();

        return producto;
    }

    public boolean editarContacto(int id, String producto, String costo, String pCostoXMayor,String stock) {

        boolean correcto = false;

        DbHelperTiendas dbHelperTiendas = new DbHelperTiendas(context);
        SQLiteDatabase db = dbHelperTiendas.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_PRODUCTOS + " SET producto = '" + producto + "', pCosto = '" + costo + "', pCostoXMayor = '" + pCostoXMayor + "', stock = '"+ stock +"' WHERE idproducto='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
}

