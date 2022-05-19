package com.example.proyectolucky.entidades;

public class Productos {
    private int idproducto;
    private String producto;
    private String pCosto;
    private String pCostoXMayor ;
    private String stock;
    private int idTienda;

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getpCosto() {
        return pCosto;
    }

    public void setpCosto(String pCosto) {
        this.pCosto = pCosto;
    }

    public String getpCostoXMayor() {
        return pCostoXMayor;
    }

    public void setpCostoXMayor(String pCostoXMayor) {
        this.pCostoXMayor = pCostoXMayor;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }
}
