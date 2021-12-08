package com.company.room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.company.room.databinding.FragmentNuevoElementoBinding;


public class NuevoElementoFragment extends Fragment {
    private FragmentNuevoElementoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentNuevoElementoBinding.inflate(inflater, container, false)).getRoot();


    }

    int contadorPuntos = 15;
    int contadorVida = 0;
    int contadorAtaque = 0;
    int contadorVelocidad = 0;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ElementosViewModel elementosViewModel = new ViewModelProvider(requireActivity()).get(ElementosViewModel.class);
        NavController navController = Navigation.findNavController(view);

        binding.sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(contadorPuntos-1 < 0){
                    binding.vida.setText("" + contadorVida);
                    binding.puntos.setText("" + contadorPuntos);
                }else{
                    contadorVida++;
                    contadorPuntos--;
                    binding.vida.setText("" + contadorVida);
                    binding.puntos.setText("" + contadorPuntos);
                }

            }
        });

        binding.restar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(contadorVida == 0){
                    binding.vida.setText("" + contadorVida);
                    binding.puntos.setText("" + contadorPuntos);
                }else{

                    contadorVida--;
                    contadorPuntos++;
                    binding.vida.setText("" + contadorVida);
                    binding.puntos.setText("" + contadorPuntos);
                }

            }
        });

        binding.sumar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(contadorPuntos-1 < 0){
                    binding.ataque.setText("" + contadorAtaque);
                    binding.puntos.setText("" + contadorPuntos);
                }else{
                    contadorAtaque++;
                    contadorPuntos--;
                    binding.ataque.setText("" + contadorAtaque);
                    binding.puntos.setText("" + contadorPuntos);
                }

            }
        });

        binding.restar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(contadorAtaque == 0){
                    binding.ataque.setText("" + contadorAtaque);
                    binding.puntos.setText("" + contadorPuntos);
                }else{
                    contadorAtaque--;
                    contadorPuntos++;
                    binding.ataque.setText("" + contadorAtaque);
                    binding.puntos.setText("" + contadorPuntos);
                }

            }
        });

        binding.sumar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(contadorPuntos-1 < 0){
                    binding.velocidad.setText("" + contadorVelocidad);
                    binding.puntos.setText("" + contadorPuntos);
                }else{
                    contadorVelocidad++;
                    contadorPuntos--;
                    binding.velocidad.setText("" + contadorVelocidad);
                    binding.puntos.setText("" + contadorPuntos);
                }

            }
        });

        binding.restar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(contadorVelocidad == 0){
                    binding.velocidad.setText("" + contadorVelocidad);
                    binding.puntos.setText("" + contadorPuntos);
                }else{
                    contadorVelocidad--;
                    contadorPuntos++;
                    binding.velocidad.setText("" + contadorVelocidad);
                    binding.puntos.setText("" + contadorPuntos);
                }

            }
        });

        binding.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.nombre.getText().toString();

                /*String descripcion = binding.descripcion.getText().toString();*/


               // int vida = Integer.parseInt(binding.vida.getText().toString());
                int vida = contadorVida;
                int ataque = contadorAtaque;
                int velocidad = contadorVelocidad;
               // int vida = Integer.parseInt(binding.vida.getText().toString());
               //int ataque = Integer.parseInt(binding.ataque.getText().toString());

                elementosViewModel.insertar(new Elemento(nombre, vida, ataque , velocidad));

               /* elementosViewModel.insertar(new Elemento(nombre, descripcion, vida, ataque));*/

                navController.popBackStack();
            }
        });






    }
}