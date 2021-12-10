package com.company.room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.company.room.databinding.FragmentFighterBinding;

import java.util.List;


public class FighterFragment extends Fragment {

    private FragmentFighterBinding binding;
    private List<Elemento> listaGuardada;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentFighterBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FighterViewModel entrenadorViewModel = new ViewModelProvider(this).get(FighterViewModel.class);

        entrenadorViewModel.obtenerEjercicio().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer ejercicio) {
                Glide.with(FighterFragment.this).load(ejercicio).into(binding.ejercicio);
            }
        });


        ElementosViewModel elementosViewModel = new ViewModelProvider(requireActivity()).get(ElementosViewModel.class);

        LiveData<List<Elemento>> lista = elementosViewModel.obtener();

        //binding.nombre.setText(lista.getValue().get(0).nombre);
        //binding.nombre2.setText(lista.getValue().get(1).nombre);
        elementosViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<Elemento>>() {

            @Override
            public void onChanged(List<Elemento> lista) {

                listaGuardada = lista;

                binding.nombre.setText(listaGuardada.get(0).nombre);
                binding.nombre2.setText(listaGuardada.get(1).nombre);
            }
        });



        /*elementosViewModel.seleccionar().observe(getViewLifecycleOwner(), new Observer<Elemento>() {
            @Override
            public void onChanged(Elemento elemento) {

                binding.nombre.setText(elemento.nombre);
                binding.nombre2.setText(elemento.nombre);

            }
        });*/


        entrenadorViewModel.obtenerRepeticion().observe(getViewLifecycleOwner(), new Observer<String>() {

            @Override
            public void onChanged(String repeticion) {
                if(repeticion.equals("¡SE ACABO!")){
                    binding.cambio.setVisibility(View.VISIBLE);



                } else {
                    binding.cambio.setVisibility(View.GONE);
                }
                binding.repeticion.setText(repeticion);


            }
        });


    }
}
