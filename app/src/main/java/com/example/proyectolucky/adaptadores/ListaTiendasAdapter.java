package com.example.proyectolucky.adaptadores;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectolucky.R;
import com.example.proyectolucky.VerActivity;
import com.example.proyectolucky.entidades.Tiendas;

import java.util.ArrayList;

public class ListaTiendasAdapter extends RecyclerView.Adapter<ListaTiendasAdapter.TiendaViewHolder>{

    ArrayList<Tiendas> listaTiendas;

    public ListaTiendasAdapter(ArrayList<Tiendas> listaTiendas){
        this.listaTiendas = listaTiendas;
    }

    @NonNull
    @Override
    public TiendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listem_item_tienda,null,false);
        return new TiendaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TiendaViewHolder holder, int position) {
        holder.viewDireccion.setText(listaTiendas.get(position).getDireccion());
        holder.viewCodigo.setText(listaTiendas.get(position).getCodigo());
        holder.viewDireccionReal.setText(listaTiendas.get(position).getDireccionReal());
    }

    @Override
    public int getItemCount() {
        return listaTiendas.size();
    }

    public class TiendaViewHolder extends RecyclerView.ViewHolder {
        TextView viewDireccion, viewCodigo, viewDireccionReal;
        Button viewBtn;
        public TiendaViewHolder(@NonNull View itemView) {
            super(itemView);
            viewDireccion = itemView.findViewById(R.id.viewDireccion);
            viewCodigo = itemView.findViewById(R.id.viewCodigo);
            viewDireccionReal = itemView.findViewById(R.id.viewDireccionReal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("¿Atenderá el pdv?") //R.string.dialog_message
                            .setTitle(listaTiendas.get(getAdapterPosition()).getDireccion())
                            .setPositiveButton("Si", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(context, VerActivity.class);
                                    intent.putExtra("IdTienda",listaTiendas.get(getAdapterPosition()).getId());
                                    context.startActivity(intent);
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    return;
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
    }
}
