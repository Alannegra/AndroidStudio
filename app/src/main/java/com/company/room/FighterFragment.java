package com.company.room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.company.room.databinding.FragmentFighterBinding;

import java.util.List;


public class FighterFragment extends Fragment  {

    private FragmentFighterBinding binding;
    private int changed = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentFighterBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ElementosViewModel elementosViewModel = new ViewModelProvider(requireActivity()).get(ElementosViewModel.class);

        FighterViewModel entrenadorViewModel = new ViewModelProvider(this).get(FighterViewModel.class);


        elementosViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<Elemento>>() {

            @Override
            public void onChanged(List<Elemento> lista) {


                if(lista.size() >= 2){

                    binding.nombre.setText(lista.get(changed).nombre);
                    binding.nombre2.setText(lista.get(changed+1).nombre);

                    Elemento luchador1 = lista.get(0);

                    Elemento luchador2 = lista.get(1);


                    int bpmaokai = 0;
                    int bpsion = 0;

                    if(luchador1.vida > luchador2.velocidad){
                        bpmaokai += luchador1.vida - luchador2.velocidad;
                    }
                    if(luchador1.ataque > luchador2.ataque){
                        bpmaokai += luchador1.ataque - luchador2.ataque;
                    }
                    if(luchador1.velocidad > luchador2.ataque){
                        bpmaokai += luchador1.velocidad - luchador2.ataque;
                    }

                    if(luchador2.vida > luchador1.velocidad){
                        bpsion += luchador2.vida - luchador1.velocidad;
                    }
                    if(luchador2.ataque > luchador1.vida){
                        bpsion += luchador2.ataque - luchador1.vida;
                    }
                    if(luchador2.velocidad > luchador1.ataque){
                        bpsion += luchador2.velocidad - luchador1.ataque;
                    }

                    if(bpmaokai == bpsion){
                        System.out.println("DRAW");
                        System.out.println("bpmaokai: " + bpmaokai);
                        System.out.println("bpsion: " + bpsion);
                        binding.cambio.setText("EMPATE");
                    }

                    if(bpmaokai > bpsion){
                        System.out.println("MAOKAI WINS " + bpmaokai);
                        binding.cambio.setText(luchador1.nombre + " Wins");
                    }
                    if(bpmaokai < bpsion){
                        System.out.println("SION WINS " + bpsion);
                        binding.cambio.setText(luchador2.nombre + " Wins");
                    }
                }

            }
        });




        entrenadorViewModel.obtenerRepeticion().observe(getViewLifecycleOwner(), new Observer<String>() {

            @Override
            public void onChanged(String repeticion) {

                if(repeticion.equals("CAMBIO")){
                    binding.cambio.setVisibility(View.VISIBLE);
                    binding.repeticion.setVisibility(View.GONE);
                    binding.nombre.setVisibility(View.GONE);
                    binding.nombre2.setVisibility(View.GONE);
                } else {
                    binding.nombre.setVisibility(View.VISIBLE);
                    binding.nombre2.setVisibility(View.VISIBLE);
                    binding.cambio.setVisibility(View.GONE);
                    binding.repeticion.setVisibility(View.VISIBLE);
                    binding.repeticion.setText(repeticion);
                    
                }


            }
        });



    }
}
