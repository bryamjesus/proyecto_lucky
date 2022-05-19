package com.example.proyectolucky.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectolucky.R;
import com.example.proyectolucky.adaptadores.ListaTiendasAdapter;
import com.example.proyectolucky.databinding.FragmentHomeBinding;
import com.example.proyectolucky.db.DbTiendas;
import com.example.proyectolucky.entidades.Tiendas;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    /*private FragmentHomeBinding binding;*/
    RecyclerView recylerTiendas;
    ArrayList<Tiendas> listaArrayTienda;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;*/
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        listaArrayTienda = new ArrayList<>();
        recylerTiendas = view.findViewById(R.id.recyclerTiendas);
        recylerTiendas.setLayoutManager(new LinearLayoutManager(getContext()));
        DbTiendas dbTiendas = new DbTiendas(getActivity());
        ListaTiendasAdapter adapter = new ListaTiendasAdapter(dbTiendas.mostrarTiendas());
        recylerTiendas.setAdapter(adapter);
        return view;
    }
/*
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

 */
}