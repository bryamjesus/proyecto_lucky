package com.example.proyectolucky.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectolucky.EditarProductoActivity;
import com.example.proyectolucky.R;
import com.example.proyectolucky.entidades.Productos;

import java.util.ArrayList;

public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.ProductoViewHolder>{
    ArrayList<Productos> listaProductos;

    public ListaProductosAdapter(ArrayList<Productos> listaProductos){
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listem_item_productos,null,false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.viewProducto.setText(listaProductos.get(position).getProducto());
        holder.viewCosto.setText(listaProductos.get(position).getpCosto());
        holder.viewCostoXMayor.setText(listaProductos.get(position).getpCostoXMayor());
        holder.viewStock.setText(listaProductos.get(position).getStock());
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView viewProducto, viewCosto, viewCostoXMayor, viewStock;
        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewProducto = itemView.findViewById(R.id.viewProducto);
            viewCosto = itemView.findViewById(R.id.viewCosto);
            viewCostoXMayor = itemView.findViewById(R.id.viewCostoXMayor);
            viewStock = itemView.findViewById(R.id.viewStock);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, EditarProductoActivity.class);
                    intent.putExtra("IdProducto",listaProductos.get(getAdapterPosition()).getIdproducto());
                    context.startActivity(intent);
                }
            });
        }
    }
}
