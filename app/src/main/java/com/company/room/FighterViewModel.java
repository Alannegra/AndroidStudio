package com.company.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class FighterViewModel extends AndroidViewModel {
    Fighter fighter;

    LiveData<Integer> ejercicioLiveData;
    LiveData<String> repeticionLiveData;

    public FighterViewModel(@NonNull Application application) {
        super(application);

        fighter = new Fighter();

        ejercicioLiveData = Transformations.switchMap(fighter.ordenLiveData, new Function<String, LiveData<Integer>>() {

            String ejercicioAnterior;

            @Override
            public LiveData<Integer> apply(String orden) {

                String ejercicio = orden.split(":")[0];

                if(!ejercicio.equals(ejercicioAnterior)){
                    ejercicioAnterior = ejercicio;
                    int imagen;
                    switch (ejercicio) {
                        case "EJERCICIO1":
                        default:
                            imagen = R.drawable.tierra2;
                            break;
                        case "EJERCICIO2":
                            imagen = R.drawable.luna2;
                            break;
                        case "EJERCICIO3":
                            imagen = R.drawable.sol2;
                            break;
                        case "EJERCICIO4":
                            imagen = R.drawable.jupiter2;
                            break;
                    }

                    return new MutableLiveData<>(imagen);
                }
                return null;
            }
        });

        repeticionLiveData = Transformations.switchMap(fighter.ordenLiveData, new Function<String, LiveData<String>>() {
            @Override
            public LiveData<String> apply(String orden) {
                return new MutableLiveData<>(orden.split(":")[1]);
            }
        });
    }

    LiveData<Integer> obtenerEjercicio(){
        return ejercicioLiveData;
    }

    LiveData<String> obtenerRepeticion(){
        return repeticionLiveData;
    }
}
